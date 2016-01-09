package com.eahackathon.watd.whosatthedoor;

import com.eahackathon.watd.whosatthedoor.models.ResponseModel;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by KienDu on 1/10/2016.
 */
public class ResponseModelUnitTest {
    @Test
    public void testResponseModel() {
        ResponseModel responseModel = new ResponseModel();
        assertNull(responseModel.getSuccess());
    }
}
