package com.eahackathon.watd.whosatthedoor;

import android.content.Intent;
import android.os.IBinder;
import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

import com.eahackathon.watd.whosatthedoor.services.GCMListenerService;

import org.junit.Test;

/**
 * Created by KienDu on 1/10/2016.
 */
public class GCMListenerServiceTest extends ServiceTestCase<GCMListenerService> {
    public GCMListenerServiceTest() {
        super(GCMListenerService.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testBindable() {
        Intent startIntent = new Intent();
        startIntent.setClass(getContext(), GCMListenerService.class);
        IBinder service = bindService(startIntent);
    }
}
