package com.example.android_qrcode_scanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AndroidQRCodeScanner extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button bScan = (Button) findViewById(R.id.button);
		bScan.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			IntentIntegrator integrator = new IntentIntegrator(this);
			integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);
			break;
			
		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {

		Log.i("MBP", "Request code: " + requestCode);
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode,
				resultCode, intent);
		String contents = result.getContents();
		if (contents != null) {
			Log.e("result found", "result found");
			showDialog(R.string.result_succeeded, result.toString());
		} else {
			Log.e("result not found", "result found");
			showDialog(R.string.result_failed,
					getString(R.string.result_failed));
		}
	}

	private void showDialog(int title, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("OK", null);

		builder.show();
	}
}