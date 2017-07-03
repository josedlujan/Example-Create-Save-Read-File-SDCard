package file.sd.save.ejemplo.com.ejemplosavesdfile;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL = 1;
    Button boton,leer;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       checkPermission();

        boton = (Button) findViewById(R.id.boton);
        leer = (Button) findViewById(R.id.leer);
        textView = (TextView) findViewById(R.id.texto);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              // createFile(view);
                createFileSpecificDir(view);
            }


        });

        leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFile(view);
            }
        });

    }



    private void checkPermission() {

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }



    public void createFile(View view){
        try {
            File myFile = new File("/sdcard/mysdfile.txt");
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
            myOutWriter.append("Texto lo que sea para poner en l archivo");
            myOutWriter.close();
            fOut.close();
            Toast.makeText(view.getContext(),"Done writing SD 'mysdfile.txt'", Toast.LENGTH_SHORT).show();
            //txtData.setText("");
        }
        catch (Exception e)
        {
            Toast.makeText(view.getContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    public void createFileSpecificDir(View view){
        try {
            String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/media/file";
            File file = new File(dir);
            file.mkdir();

            File myFile = new File(dir,"newsdfile.txt");
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
            myOutWriter.append("Texto lo que sea para poner en l archivo");
            myOutWriter.close();
            fOut.close();
            Toast.makeText(view.getContext(),"Done writing SD 'mysdfile.txt'", Toast.LENGTH_SHORT).show();
            //txtData.setText("");
        }
        catch (Exception e)
        {
            Toast.makeText(view.getContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }


    public void readFile(View view){
        try {

            File myFile = new File("/sdcard/mysdfile.txt");
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = "";
            while ((aDataRow = myReader.readLine()) != null)
            {
                aBuffer += aDataRow ;
            }
            textView.setText(aBuffer);
            myReader.close();
            Toast.makeText(view.getContext(),"Done reading SD 'mysdfile.txt'",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(view.getContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        }
    }


