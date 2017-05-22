package com.romazzz.gmclient.ui.login;

import android.widget.Button;

import com.romazzz.gmclient.BuildConfig;
import com.romazzz.gmclient.R;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
/**
 * Created by z01tan on 21/05/2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class RoboelectricLoginViewTest {
    private LoginView loginView;

    @Before
    public void setUp() throws Exception {
        loginView = Robolectric.buildActivity(LoginView.class).
                create().
                resume().
                get();
    }

    @Test
    public void exampleTest() throws Exception{
        Button button = (Button) loginView.findViewById(R.id.login_btn);
        String buttonText = button.getText().toString();
        assertEquals(buttonText, "LOGIN");
    }
}
