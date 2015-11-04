package com.payu.test;

import org.apache.cordova.DroidGap;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.widget.Toast;



public class PaymentActivity extends DroidGap {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.init();
		initWebView();
	}

	/**
	 * Function initializes  webview & does the necessary settings for webview
	 */

	private void initWebView(){
		// loading application url
		appView.loadUrl("file:///android_asset/main.html");
		// Adding javascript interface
		appView.addJavascriptInterface(new Object(){
			@JavascriptInterface
			public void onResponse(final String postParams){
				appView.post(new Runnable(){
					@Override
					public void run() {
						// We have received the post params. 
						// lets make payment.
						makePayment(postParams);
					}
					
					
				});
				
			}
			
			@JavascriptInterface
			public void onSuccess(final String response)
			{
				appView.post(new Runnable() {
					
					@Override
					public void run() {
						
						Intent intent = new Intent();
                        intent.putExtra("result", response);
                        setResult(RESULT_OK, intent);
                        finish();
						
					}
				});
			}
			
			@JavascriptInterface
			public void onFailure(final String response)
			{
				appView.post(new Runnable() {
					
					@Override
					public void run() {
						Intent intent = new Intent();
                        intent.putExtra("result", response);
                        setResult(RESULT_CANCELED, intent);
                        finish();
						
					}
				});
			}
			
		}, "PayU");
	}
	
	
	public void makePayment(String postParams){
		// production url https://secure.payu.in/_payment
		//appView.postUrl("https://secure.payu.in/_payment", postParams.toString().getBytes());
		
		//testing url https://test.payu.in/_payment
		appView.postUrl("https://test.payu.in/_payment", postParams.toString().getBytes());
	}

}