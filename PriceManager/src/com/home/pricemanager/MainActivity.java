package com.home.pricemanager;

import java.text.NumberFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	private EditText edittxtVw_1g;
	private EditText edittxtVw_5g;
	private EditText edittxtVw_10g;
	private EditText edittxtVw_50g;
	private EditText edittxtVw_100g;
	private EditText edittxtVw_200g;
	private EditText edittxtVw_500g;
	private EditText edittxtVw_1kg;
	private EditText edittxtVw_custom_quantity;
	private EditText edittxtVw_custom_value;

	private float price_1g;
	private float price_5g;
	private float price_10g;
	private float price_50g;
	private float price_100g;
	private float price_200g;
	private float price_500g;
	private float price_1kg;
	private float price_custom_value;
	private float price_custom_quantity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initUI();
	}

	private void initUI() {
		Button getButton = (Button) findViewById(R.id.button1);
		Button resetButton = (Button) findViewById(R.id.button2);
		edittxtVw_1g = (EditText) findViewById(R.id.editText1);
		edittxtVw_5g = (EditText) findViewById(R.id.editText2);
		edittxtVw_10g = (EditText) findViewById(R.id.editText3);
		edittxtVw_50g = (EditText) findViewById(R.id.editText4);
		edittxtVw_100g = (EditText) findViewById(R.id.editText5);
		edittxtVw_200g = (EditText) findViewById(R.id.editText6);
		edittxtVw_500g = (EditText) findViewById(R.id.editText7);
		edittxtVw_1kg = (EditText) findViewById(R.id.editText8);
		edittxtVw_custom_quantity = (EditText) findViewById(R.id.editText10);
		edittxtVw_custom_value = (EditText) findViewById(R.id.editText9);
		getButton.setOnClickListener(this);
		resetButton.setOnClickListener(this);
		setText();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			setEnabled(false);
			processPrice();
			setText();
			break;
		case R.id.button2:
			setEnabled(true);
			price_1g = 0;
			price_5g = 0;
			price_10g = 0;
			price_50g = 0;
			price_100g = 0;
			price_200g = 0;
			price_500g = 0;
			price_1kg = 0;
			price_custom_value = 0;
			price_custom_quantity = 0;
			setText();
			break;

		default:
			break;
		}

	}

	private void processPrice() {
		if (edittxtVw_custom_quantity.getText().toString().length() > 0 && edittxtVw_custom_value.getText().toString().length() > 0) {
			try {
				price_custom_value = Float.valueOf(edittxtVw_custom_value.getText().toString());
				price_custom_quantity =Float.valueOf(edittxtVw_custom_quantity.getText().toString());
				if(price_custom_quantity>0){
					price_1g = price_custom_value / Float.valueOf(price_custom_quantity);
				}else{
					showError("Quantity cannot be 0");
				}
			} catch (NumberFormatException e) {
				showError("Please Check the format");
			}
		}
		else if (edittxtVw_1g.getText().toString().length() > 0) {
			try {
				price_1g = Float.valueOf(edittxtVw_1g.getText().toString());
			} catch (NumberFormatException e) {
				showError("Please Check the format");
			}
		} else if (edittxtVw_5g.getText().toString().length() > 0) {
			try {
				price_5g = Float.valueOf(edittxtVw_5g.getText().toString());
			} catch (NumberFormatException e) {
				showError("Please Check the format");
			}
			price_1g = price_5g / 5;
		} else if (edittxtVw_10g.getText().toString().length() > 0) {
			try {
				price_10g = Float.valueOf(edittxtVw_10g.getText().toString());
			} catch (NumberFormatException e) {
				showError("Please Check the format");
			}
			price_1g = price_10g / 10;
		} else if (edittxtVw_50g.getText().toString().length() > 0) {
			try {
				price_50g = Float.valueOf(edittxtVw_50g.getText().toString());
			} catch (NumberFormatException e) {
				showError("Please Check the format");
			}
			price_1g = price_50g / 50;
		} else if (edittxtVw_100g.getText().toString().length() > 0) {
			try {
				price_100g = Float.valueOf(edittxtVw_100g.getText().toString());
			} catch (NumberFormatException e) {
				showError("Please Check the format");
			}
			price_1g = price_100g / 100;
		} else if (edittxtVw_200g.getText().toString().length() > 0) {
			try {
				price_200g = Float.valueOf(edittxtVw_200g.getText().toString());
			} catch (NumberFormatException e) {
				showError("Please Check the format");
			}
			price_1g = price_200g / 200;
		} else if (edittxtVw_500g.getText().toString().length() > 0) {
			try {
				price_500g = Float.valueOf(edittxtVw_500g.getText().toString());
			} catch (NumberFormatException e) {
				showError("Please Check the format");
			}
			price_1g = price_500g / 500;
		} else if (edittxtVw_1kg.getText().toString().length() > 0) {
			try {
				price_1kg = Float.valueOf(edittxtVw_1kg.getText().toString());
			} catch (NumberFormatException e) {
				showError("Please Check the format");
			}
			price_1g = price_1kg / 1000;
		} else {
			showError("Please enter the price");
		}
		price_5g = price_1g * 5;
		price_10g = price_1g * 10;
		price_50g = price_1g * 50;
		price_100g = price_1g * 100;
		price_200g = price_1g * 200;
		price_500g = price_1g * 500;
		price_1kg = price_1g * 1000;
		price_custom_value = price_1g * price_custom_quantity;

	}

	private void showError(String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setMessage(message);
		builder.setTitle("Error");
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				setEnabled(true);
				dialog.dismiss();
			}
		});
		builder.show();

	}

	public void setText() {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(0);
		edittxtVw_1g.setText(price_1g > 0 ? nf.format(price_1g) + "" : "");
		edittxtVw_5g.setText(price_5g > 0 ? nf.format(price_5g) + "" : "");
		edittxtVw_10g.setText(price_10g > 0 ? nf.format(price_10g) + "" : "");
		edittxtVw_50g.setText(price_50g > 0 ? nf.format(price_50g) + "" : "");
		edittxtVw_100g.setText(price_100g > 0 ? nf.format(price_100g) + "" : "");
		edittxtVw_200g.setText(price_200g > 0 ? nf.format(price_200g) + "" : "");
		edittxtVw_500g.setText(price_500g > 0 ? nf.format(price_500g)  + "" : "");
		edittxtVw_1kg.setText(price_1kg > 0 ? nf.format(price_1kg) + "" : "");
		edittxtVw_custom_quantity.setText(price_custom_quantity > 0 ? nf.format(price_custom_quantity) + "" : "");
		edittxtVw_custom_value.setText(price_custom_value > 0 ? nf.format(price_custom_value) + "" : "");
	}

	public void setEnabled(boolean value) {
		edittxtVw_1g.setEnabled(value);
		edittxtVw_5g.setEnabled(value);
		edittxtVw_10g.setEnabled(value);
		edittxtVw_50g.setEnabled(value);
		edittxtVw_100g.setEnabled(value);
		edittxtVw_200g.setEnabled(value);
		edittxtVw_500g.setEnabled(value);
		edittxtVw_1kg.setEnabled(value);
		edittxtVw_custom_quantity.setEnabled(value);
		edittxtVw_custom_value.setEnabled(value);

	}
}
