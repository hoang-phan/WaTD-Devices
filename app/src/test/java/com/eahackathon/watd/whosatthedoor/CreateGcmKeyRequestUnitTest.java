package com.eahackathon.watd.whosatthedoor;

import com.eahackathon.watd.whosatthedoor.models.CreateGcmKeyRequest;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by KienDu on 1/10/2016.
 */
public class CreateGcmKeyRequestUnitTest {

    public static String TEST_DEVICE_ID = "test_device_id";
    public static String TEST_REG_ID = "test_reg_id";

    @Test
    public void testCreateGcmKeyRequest_getter_setter() {
        CreateGcmKeyRequest createGcmKeyRequest = new CreateGcmKeyRequest("", "");
        createGcmKeyRequest.setDeviceId(TEST_DEVICE_ID);
        createGcmKeyRequest.setRegId(TEST_REG_ID);
        assertEquals(TEST_DEVICE_ID, createGcmKeyRequest.getDeviceId());
        assertEquals(TEST_REG_ID, createGcmKeyRequest.getRegId());
    }
}
