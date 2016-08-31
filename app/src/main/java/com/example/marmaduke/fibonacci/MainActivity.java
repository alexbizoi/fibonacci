package com.example.marmaduke.fibonacci;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNr;
    private Button buttonCalc;
    private ListView listView;
    private ArrayAdapter<String> mAdapter;

    ArrayList<String> FibonacciList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      initUIComp();

        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAdapter();
            }
        });
    }


    private static String fibonacci(int fib_nr)
    {
        String fib1,fib2, fib_final;
        fib1="1";
        fib2="1";
        fib_final="1";
        if( fib_nr == 1 || fib_nr == 2 )
            return "1";
        else
            for( int i=3;i<=fib_nr; i++)
            {
                fib_final= addDigit(fib1,fib2);
                fib1=fib2;
                fib2=fib_final;
            }
        return fib_final;
    }

    public static String addDigit(String d1, String d2) {
        StringBuilder buf = new StringBuilder();
        for ( int i1 = d1.length() - 1, i2 = d2.length() - 1, carry = 0;
              i1 >= 0 || i2 >= 0 || carry != 0;
              i1--, i2-- ) {
            int digit1 = i1 < 0 ? 0 :
                    Integer.parseInt(Character.toString(d1.charAt(i1)));
            int digit2 = i2 < 0 ? 0 :
                    Integer.parseInt(Character.toString(d2.charAt(i2)));

            int digit = digit1 + digit2 + carry;
            if (digit > 9) {
                carry = 1;
                digit -= 10;
            } else {
                carry = 0;
            }

            buf.append(digit);
        }
        return buf.reverse().toString();
    }

    private void initUIComp()
    {
        editTextNr= (EditText) findViewById(R.id.editTextNr);
        buttonCalc= (Button) findViewById(R.id.buttonCalc);
        listView = (ListView) findViewById(R.id.listView);

    }

    private void setAdapter()
    {
        int number = Integer.parseInt(editTextNr.getText().toString());
        FibonacciList.clear();
        for(int i=0;i<=number;i++)
            FibonacciList.add(fibonacci(i));
               mAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.row,R.id.textView,FibonacciList);
        listView.setAdapter(mAdapter);
    }
}
