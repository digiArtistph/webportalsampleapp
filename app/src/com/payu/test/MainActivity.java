package com.payu.test;

import org.apache.cordova.DroidGap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends DroidGap {
	

	private static final int PAYU_REQUEST_CODE = 100;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//super.init();

		Button click = new Button(this);
		click.setText("Click to Pay.");
		click.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			
				startActivityForResult(new Intent(getApplicationContext(), PaymentActivity.class),PAYU_REQUEST_CODE);

			}
		});

		setContentView(click);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
//		super.onActivityResult(requestCode, resultCode, intent);
		
		if (requestCode == PAYU_REQUEST_CODE) {
            if(intent != null ) {
//                new AlertDialog.Builder(this)
//                        .setCancelable(false)
//                        .setMessage(intent.getStringExtra("result"))
//                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                            }
//                        }).show();
            	super.init();
                initWebView();
            	
            }else{
                Toast.makeText(this, "Could not receive data", Toast.LENGTH_LONG).show();
            }
        }
	}	
	

		//appView.loadUrl("file:///android_asset/afterCompletion.html");
	/**
	 * Function initializes  webview & does the necessary settings for webview
	 */

	private void initWebView(){
		// loading application url
//		super.loadUrl("file:///android_asset/afterCompletion.html");
		appView.postUrl("https://payu.herokuapp.com/success", "Data".getBytes());
//		appView.loadUrl("file:///android_asset/afterCompletion.html");
		//appView.addJavascriptInterface(null, "PayU");
		
	}
	
	
	
}
