package com.example.darrensantana.calculator;

import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import static java.lang.Double.NaN;

public class MainActivity extends FragmentActivity {
Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDecimal,
        btnClear, btnDivide, btnMultiply, btnAdd, btnSubtract, btnPercent,
        btnPositiveOrNevativeSign, btnEquals, btnSquared, btnCubed, btnSquareRoot, btnCubedRoot, btnPi;
    TextView display;
    Double firstNumber, secondNumber, total;
    String operation, operation2, secondaryOperation;
    boolean isNegative =false;
    Double temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.txtDisplay);
        buttons();
        onClick();

    }
    public void buttons(){
        btn1 = (Button) findViewById(R.id.btnOne);
        btn2 = (Button) findViewById(R.id.btnTwo);
        btn3 = (Button) findViewById(R.id.btnThree);
        btn4 = (Button) findViewById(R.id.btnFour);
        btn5 = (Button) findViewById(R.id.btnFive);
        btn6 = (Button) findViewById(R.id.btnSix);
        btn7 = (Button) findViewById(R.id.btnSeven);
        btn8 = (Button) findViewById(R.id.btnEight);
        btn9 = (Button) findViewById(R.id.btnNine);
        btn0 = (Button) findViewById(R.id.btnZero);
        btnDecimal = (Button) findViewById(R.id.btnDec);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnDivide = (Button) findViewById(R.id.btnDiv);
        btnMultiply = (Button) findViewById(R.id.btnMult);
        btnAdd = (Button) findViewById(R.id.btnPlus);
        btnSubtract = (Button) findViewById(R.id.btnMin);
        btnPercent = (Button)findViewById(R.id.btnPercent);
        btnCubed = (Button)findViewById(R.id.btnCubed);
        btnSquared = (Button)findViewById(R.id.btnSquared);
        btnSquareRoot = (Button)findViewById(R.id.btnSquareRoot);
        btnCubedRoot = (Button)findViewById(R.id.btnCubedSquareRoot);
        btnPi = (Button)findViewById(R.id.btnPi);
        btnPositiveOrNevativeSign = (Button)findViewById(R.id.btnPositiveOrNevativeSign);
        btnEquals = (Button) findViewById(R.id.btnEquals);

    }


    public void onClick(){

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation = "";
                display.setText("");
                operation2 ="";
                temp = null;
                firstNumber = null;
                secondNumber = null;
                isNegative = false;
                total = null;
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(total != null) {
                    display.setText("");
                    total = null;
                }
                display.setText(display.getText()+"1");

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(total != null) {
                    display.setText("");
                    total = null;
                }
                display.setText(display.getText()+"2");

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(total != null) {
                    display.setText("");
                    total = null;
                }
                display.setText(display.getText()+"3");

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(total != null) {
                    display.setText("");
                    total = null;
                }
                display.setText(display.getText()+"4");

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(total != null) {
                    display.setText("");
                    total = null;
                }
                display.setText(display.getText()+"5");

            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(total != null) {
                    display.setText("");
                    total = null;
                }
                display.setText(display.getText()+"6");

            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(total != null) {
                    display.setText("");
                    total = null;
                }
                display.setText(display.getText()+"7");

            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(total != null) {
                    display.setText("");
                    total = null;
                }
                display.setText(display.getText()+"8");

            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(total != null) {
                    display.setText("");
                    total = null;
                }
                    display.setText(display.getText()+"9");



            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(total != null) {
                    display.setText("");
                    total = null;
                }
                display.setText(display.getText()+"0");

            }
        });

        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(total != null)
                    display.setText("");

                display.setText(display.getText()+".");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    firstNumber = Double.parseDouble((String) display.getText());

                }catch (Exception e){}

                display.setText("");
                operation = "Add";
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    firstNumber = Double.parseDouble((String) display.getText());

                }catch (Exception e){}

                display.setText("");
                operation = "Subtract";
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    firstNumber = Double.parseDouble((String) display.getText());

                }catch (Exception e){}

                display.setText("");
                operation = "Multiply";
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    firstNumber = Double.parseDouble((String) display.getText());

                }catch (Exception e){}

                display.setText("");
                operation = "Divide";
            }
        });

        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    firstNumber = Double.parseDouble((String) display.getText());

                }catch (Exception e){}

                total = firstNumber/100;
                display.setText(total.toString());
            }
        });

        btnPositiveOrNevativeSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if(!isNegative) {
//                    try {
//                        firstNumber = Double.parseDouble((String) display.getText());
//
//                    } catch (Exception e) {
//                    }
//                    temp = firstNumber;
//                }
//
//                if(isNegative){
//                    display.setText(temp.toString());
//                    isNegative = false;
//                }else {
//                    if(display.getText() == ""){
//                        display.setText("-");
//                        isNegative = true;
//                    }else {
//                        display.setText("-" + firstNumber.toString());
//                        isNegative = true;
//                    }
//                }
                if (!isNegative || display.getText() != "") {
                    try {
                        firstNumber = Double.parseDouble((String) display.getText());

                    } catch (Exception e) {
                    }
                    temp = firstNumber;
                }

                if(isNegative){
                    operation2 = "Positive";
                }else{
                    operation2 = "Negative";
                }

                switch (operation2){

                    case "Negative":
                        if(display.getText() != ""){
                            display.setText("-"+display.getText());

                        }else{
                            display.setText("-");
                        }
                        isNegative = true;
                        break;

                    case "Positive":
                        if(display.getText() != "-") {
                            Double x = Math.abs(temp);
                            display.setText(x.toString());
                        }else{
                            display.setText("");
                        }
                        isNegative = false;
                        break;

                }
            }
        });



        btnEquals.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                secondNumber = Double.parseDouble((String) display.getText());
                display.setText("");
                switch (operation){

                    case "Add":
                        total = firstNumber + secondNumber;
                        break;

                    case "Subtract":
                        total = firstNumber - secondNumber;
                        break;

                    case "Multiply":
                        total = firstNumber * secondNumber;
                        break;

                    case "Divide":
                        total = firstNumber / secondNumber;
                        break;

                }
                display.setText(total.toString());
                //formatter.format(total);

            }
        });

        btnPi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    firstNumber = Double.parseDouble((String) display.getText());

                }catch (Exception e){}
                secondaryOperations("pi");
            }
        });

        btnCubed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    firstNumber = Double.parseDouble((String) display.getText());

                }catch (Exception e){}
                secondaryOperations("cubed");
            }
        });

        btnCubedRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    firstNumber = Double.parseDouble((String) display.getText());

                }catch (Exception e){}
                secondaryOperations("cubedRoot");
            }
        });

        btnSquareRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    firstNumber = Double.parseDouble((String) display.getText());

                }catch (Exception e){}
                secondaryOperations("squaredRoot");
            }
        });

        btnSquared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    firstNumber = Double.parseDouble((String) display.getText());

                }catch (Exception e){}
                secondaryOperations("squared");
            }
        });
    }



    public void secondaryOperations(String secondaryOperation){
        switch (secondaryOperation){

            case "pi":
                total = 3.141592653589793;
                break;

            case "cubed":
                total = firstNumber * firstNumber * firstNumber;
                break;

            case "squared":
                total = firstNumber * firstNumber;
                break;

            case "squaredRoot":
                total = Math.sqrt(firstNumber);
                break;

            case "cubedRoot":
                total = Math.cbrt(firstNumber);
                break;

        }
        display.setText(total.toString());
    }
}
