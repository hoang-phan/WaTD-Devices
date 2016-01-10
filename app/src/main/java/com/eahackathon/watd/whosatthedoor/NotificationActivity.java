package com.eahackathon.watd.whosatthedoor;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eahackathon.watd.whosatthedoor.models.ResponseModel;
import com.eahackathon.watd.whosatthedoor.models.UpdatePersonNameRequest;
import com.eahackathon.watd.whosatthedoor.network.APIService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class NotificationActivity extends AppCompatActivity {
    private TextView mPersonTv;
    private ImageView mPersonIv;
    private Button mOkBtn, mAnotherBtn;
    private EditText mPersonEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        byte[] data = getIntent().getByteArrayExtra(Constants.PERSON_DATA);
        final String id = getIntent().getStringExtra(Constants.PERSON_ID);
        final String name = getIntent().getStringExtra(Constants.PERSON_NAME);

        mPersonTv = (TextView)findViewById(R.id.tv_person);
        mPersonEt = (EditText)findViewById(R.id.et_person);
        mPersonIv = (ImageView)findViewById(R.id.iv_person);
        mOkBtn = (Button)findViewById(R.id.btn_ok);
        mAnotherBtn = (Button)findViewById(R.id.btn_another);

        mPersonTv.setText(name);
        mPersonEt.setText(name);

        mPersonIv.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseModel> call = APIService.getInstance().updatePersonName(id,
                        new UpdatePersonNameRequest(mPersonEt.getText().toString()));
                call.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Response<ResponseModel> response, Retrofit retrofit) {
                        Log.d("TAG", "success");
                        finish();
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }
        });

        mAnotherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPersonEt.setVisibility(View.VISIBLE);
                mPersonTv.setVisibility(View.GONE);
                mAnotherBtn.setVisibility(View.GONE);
            }
        });
    }
}
