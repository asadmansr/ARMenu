package com.PulsarLabs.ARMenu;

import com.unity3d.player.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @deprecated Use UnityPlayerActivity instead.
 */
public class UnityPlayerProxyActivity extends Activity
{
	@Override protected void onCreate (Bundle savedInstanceState)
	{
		//Log.w("Unity", "UnityPlayerNativeActivity has been deprecated, please update your AndroidManifest to use UnityPlayerActivity instead");
		super.onCreate(savedInstanceState);

		Intent intent = new Intent(this, com.PulsarLabs.ARMenu.UnityPlayerActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		Bundle extras = getIntent().getExtras();
		if (extras != null)
			intent.putExtras(extras);
		startActivity(intent);
	}
}
