package com.tenchael.upclient;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.http.Header;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MainActivity extends ActionBarActivity {
	protected static final String TAG = "MainActivity";
	private Context context;
	private EditText mEditTextPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		mEditTextPath = (EditText) findViewById(R.id.et_path);
	}

	public void upload(View view) {
		String path = mEditTextPath.getText().toString().trim();
		File file = new File(path);
		if (file != null && file.length() > 0) {
			AsyncHttpClient client = new AsyncHttpClient();
			RequestParams params = new RequestParams();
			try {
				params.put("file", new File(path));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} // Upload a File
			client.post("http://192.168.1.107:8080/upload-server/upload",
					params, new AsyncHttpResponseHandler() {

						@Override
						public void onSuccess(int statusCode, Header[] headers,
								byte[] responseBody) {
							Log.i(TAG, "code=" + statusCode);
							Toast.makeText(context, "文件上传成功",
									Toast.LENGTH_SHORT).show();
							;

						}

						@Override
						public void onFailure(int statusCode, Header[] headers,
								byte[] responseBody, Throwable error) {
							Log.e(TAG, "code=" + statusCode);
							Toast.makeText(context, "文件上传失败",
									Toast.LENGTH_SHORT).show();

						}

					});
		} else {
			Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
		}

	}
}
