package com.eahackathon.watd.whosatthedoor;

import com.eahackathon.watd.whosatthedoor.network.APIService;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by KienDu on 1/10/2016.
 */
public class APIServiceUnitTest {
    @Test
    public void testAPIServiceUnitTest() {
        assertNotNull(APIService.getInstance());
    }
}
