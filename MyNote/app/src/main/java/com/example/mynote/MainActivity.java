package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {


    EditText txtWords;
    TextView txtSize;
    Button btnBig;
    Button btnSmall;
    CheckBox cbxBold;
    CheckBox cbxUnderline;
    Spinner spColor;
    Spinner spFont;
    RadioButton rbLTR;
    RadioButton rbRTL;
    EditText txtFileName;
    Button btnNew;
    Button btnSave;
    Button btnGetFile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         txtWords = findViewById(R.id.txtWords);
         txtSize = findViewById(R.id.txtSize);
         btnBig = findViewById(R.id.btnBig);
         btnSmall = findViewById(R.id.btnSmall);
         cbxBold = findViewById(R.id.cbxBold);
         cbxUnderline = findViewById(R.id.cbxUnderline);
         spColor = findViewById(R.id.spColor);
         spFont = findViewById(R.id.spFont);
         rbLTR = findViewById(R.id.rbLTR);
         rbRTL = findViewById(R.id.rbRTL);
         txtFileName = findViewById(R.id.txtFileName);
         btnNew = findViewById(R.id.btnNew);
         btnSave = findViewById(R.id.btnSave);
         btnGetFile = findViewById(R.id.btnGetFile);


        fillColor();
        fillFont();


        btnBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText('+');
            }
        });

        btnSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText('-');
            }
        });

        cbxBold.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setBold();
               // chooseFont();
            }
        });

        cbxUnderline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setUnderline();
            }
        });


        spColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chooseColor();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spFont.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chooseFont();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        rbLTR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setAlignment();
            }
        });


        rbRTL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setAlignment();
            }
        });



        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newFile();
            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFile();
            }
        });

        btnGetFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFile();
            }
        });





    }

    protected void fillColor()
    {
        String[]strColor = {

                "Black",
                "White",
                "Red",
                "Green",
                "Blue",
                "Gray",
                "Yellow",
                "Orange"

        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this , android.R.layout.simple_spinner_item , strColor);
        spColor.setAdapter(adapter);

    }

    protected void chooseColor()
    {

        String strColor = spColor.getSelectedItem().toString();

        switch (strColor)
        {
            case "Black": txtWords.setTextColor(getResources().getColor(R.color.Black));break;
            case "White": txtWords.setTextColor(getResources().getColor(R.color.White));break;
            case "Red": txtWords.setTextColor(getResources().getColor(R.color.Red));break;
            case "Green": txtWords.setTextColor(getResources().getColor(R.color.Green));break;
            case "Blue": txtWords.setTextColor(getResources().getColor(R.color.Blue));break;
            case "Gray": txtWords.setTextColor(getResources().getColor(R.color.Gray));break;
            case "Yellow": txtWords.setTextColor(getResources().getColor(R.color.Yellow));break;
            case "Orange": txtWords.setTextColor(getResources().getColor(R.color.Orange));break;
        }
    }


    protected void fillFont()
    {
        String[] strFont = {

                "SANS_SERIF",  // it is exist by default and not setting in assets file but you write it to appear upper of font s types
                "ARABSQ",
                "arabtype",
                "BLDITLAR",
                "CHILLER",
                "CURLZ",
                "FREESCPT",
                "OLDANDEC"


        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1,strFont);
        spFont.setAdapter(adapter);
    }



    protected void chooseFont()
    {

        String strFont = spFont.getSelectedItem().toString();


        Typeface tf ;//= Typeface.SANS_SERIF;
           // it is instead of doing default case in switch and if you did not set default it would give you an error


        if("SANS_SERIF".equals(strFont))
            tf = Typeface.SANS_SERIF;
        else if("arabtype".equals(strFont))
            tf = Typeface.createFromAsset(getAssets(),strFont+".ttf");  // because it is lowercase in assets file
        else
            tf = Typeface.createFromAsset(getAssets(),strFont+".TTF");






/* // this is instead of the switch case


        switch (strFont)
        {

           case "SANS_SERIF":
              //  tf = Typeface.createFromAsset(getAssets(),"SANS_SERIF.TTF");  // dont do it ... no sans serif in assets file
                tf = Typeface.SANS_SERIF;  // do it or dont do and it will work correctly
                break;


            case "ARABSQ":
                tf = Typeface.createFromAsset(getAssets(),"ARABSQ.TTF");
                break;
            case "arabtype":
                tf = Typeface.createFromAsset(getAssets(),"arabtype.ttf");
                break;
            case "BLDITLAR":
                tf = Typeface.createFromAsset(getAssets(),"BLDITLAR.TTF");
                break;
            case "CHILLER":
                tf = Typeface.createFromAsset(getAssets(),"CHILLER.TTF");
                break;
            case "CURLZ":
                tf = Typeface.createFromAsset(getAssets(),"CURLZ.TTF");
                break;
            case "FREESCPT":
                tf = Typeface.createFromAsset(getAssets(),"FREESCPT.TTF");
                break;
            case "OLDANDEC":
                tf = Typeface.createFromAsset(getAssets(),"OLDANDEC.TTF");
                break;
        }

*/

        if(cbxBold.isChecked())
            txtWords.setTypeface(tf , Typeface.BOLD);
        else
            txtWords.setTypeface(tf , Typeface.NORMAL);

        // if you don't do this.. when you check Bold cbx ,,
        // font will be reset to just bold
        // and we will do another operation in setBold function..it is like connection
    }


    protected void editText(char sign)
    {
        int size = Integer.parseInt(txtSize.getText().toString());

        switch (sign)
        {
            case '+':
                size++;
                break;
            case '-':
                size--;
                break;
        }

        if(size>50) size = 50;
        if(size<7)  size = 7;

        txtWords.setTextSize(size);
        txtSize.setText(size+"");

    }


    protected void setBold()
    {

       /*
        if(cbxBold.isChecked())
            txtWords.setTypeface(null, Typeface.BOLD);
        else
            txtWords.setTypeface(null , Typeface.NORMAL);
        */

        chooseFont();  // this is the operation we talk about
        // and it canceled the above operation of function

    }

    // we can replace this function by just calling chhoseFont function above



    protected void setUnderline()
    {


        if(cbxUnderline.isChecked())
            txtWords.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        else
            txtWords.setPaintFlags(Paint.LINEAR_TEXT_FLAG);


    }


    protected void setAlignment()
    {

        if(rbLTR.isChecked())
            txtWords.setGravity(Gravity.LEFT);
        else
            txtWords.setGravity(Gravity.RIGHT);

    }// this function doesnt work , i dont know why


    protected void newFile()
    {
        // i wanna reset all the features , so...

        txtWords.setText("");
        txtSize.setText("18");
        txtWords.setTextSize(18);
        cbxBold.setChecked(false);
        cbxUnderline.setChecked(false);
        spColor.setSelection(0);  // it is to choose the first color in the spinner
        spFont.setSelection(0);  // the same for fonts
        rbLTR.setChecked(true);
        txtFileName.setText("Name");
        txtWords.requestFocus();  // it is just to let the cursor standing on txtWords


    }


    protected void saveFile()
    {
       // String fName = txtFileName.getText().toString().trim();

        if("".equals(txtFileName.getText().toString().trim()))  // to ensure that the file name entered is not empty
        {
            Toast.makeText(this, "File Name Is Empty !!", Toast.LENGTH_SHORT).show();
            txtFileName.requestFocus();
        }
        else
        {
            try  // printwriter must have try and catch
            {

               // String strPath = Environment.getExternalStorageDirectory().getPath()+"/MyWords";  // it doesnt work

                String strPath = getExternalFilesDir(null).getPath()+"/MyNote";  // it is the path that the file will be saved in

                File f = new File(strPath);

                    f.mkdir();  //create folder in the path strPath

                PrintWriter pw = new PrintWriter(strPath + "/"+txtFileName.getText()+".txt");  //it is like the file we will write in with the extention .txt

                pw.write(txtWords.getText().toString());  // it lets us write in the file

                pw.close();  //we have to close the file




                //this printwriter is to save the properties of the note

                PrintWriter pwSettings = new PrintWriter(strPath + "/"+txtFileName.getText()+"Settings.txt");

                String strSettings =

                             txtSize.getText()+
                        "\n"+cbxBold.isChecked()+
                        "\n"+cbxUnderline.isChecked()+
                        "\n"+spColor.getSelectedItem()+
                        "\n"+spFont.getSelectedItem()+
                        "\n"+rbLTR.isChecked()+
                        "\n"+rbRTL.isChecked();

                pwSettings.write(strSettings);
                pwSettings.close();

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();


            }catch (Exception ex)
            {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            }

        }

    }




    protected void getFile()
    {
        try{

          //  String strPath = Environment.getExternalStorageDirectory().getPath()+"/MyNote";


            String strPath = getExternalFilesDir(null).getPath()+"/MyNote";
            FileReader fr = new FileReader(strPath + "/" + txtFileName.getText() + ".txt");
            BufferedReader br = new BufferedReader(fr);

            String strContent = "";
            String strLine ="";

            while( ( strLine = br.readLine() ) != null )
            {
                strContent += strLine+"\n";
            }




            fr = new FileReader(strPath + "/" + txtFileName.getText() + "Settings.txt");
            br = new BufferedReader(fr);

            String [] arrSettings = new String[7];
            int numOfIndex=0;

            while ( (strLine = br.readLine() ) != null )
            {

                arrSettings[numOfIndex] = strLine;
                numOfIndex++;

            }

            fr.close();
            br.close();



            txtSize.setText(arrSettings[0]);
            txtWords.setTextSize(Integer.parseInt(arrSettings[0]));
            cbxBold.setChecked(Boolean.parseBoolean(arrSettings[1]));
            cbxUnderline.setChecked(Boolean.parseBoolean(arrSettings[2]));

            spColor.setSelection(((ArrayAdapter<String>)spColor.getAdapter()).getPosition(arrSettings[3]));
            spFont.setSelection(((ArrayAdapter<String>)spFont.getAdapter()).getPosition(arrSettings[4]));

            if("true".equals(arrSettings[5])) rbLTR.setChecked(true);
            else rbRTL.setChecked(true);

            txtWords.setText(strContent);



        }catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }



    }


}
