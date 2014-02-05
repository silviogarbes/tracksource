package br.org.tracksource;

import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Poi extends Activity {
	protected static final String TAG = null;
    Object locationListener = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poi);
        
        ativaGps();
        locationListener = coletaDadosGps();
        carregaMenuCategoria();
        ativaBotoes();
    }
    
    public void ativaBotoes(){
    	Button botaoComporEmail = (Button) findViewById(R.id.botaoComporEmail);
        botaoComporEmail.setOnClickListener(new Button.OnClickListener() {
	        public void onClick(View v){
	        	comporEmail();
        }});
    }
    
    
    public void ativaGps(){
    	LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    	if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
    	{
    		// Processo automatico
    		/*
    	    Intent intent = new Intent();
    	    intent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
    	    intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
    	    intent.setData(Uri.parse("3"));
    	    sendBroadcast(intent);
    	    */
    		/*
    	    Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
    	    intent.putExtra("enabled", true);
    	    sendBroadcast(intent);
    	    
    	    Toast.makeText(getApplicationContext(), "GPS Ativado", Toast.LENGTH_LONG).show();
    	    */
    		
    	    // Processo manual
    	    
    	    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);   
    	    startActivityForResult(intent, 1);
    	    Toast.makeText(getApplicationContext(), "Ative o GPS", Toast.LENGTH_LONG).show();
    	    
    	}
    }
    
    public void desativaGps(){
    	LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    	if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
    	{
    		/*
    	    Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
    	    intent.putExtra("enabled", false);
    	    sendBroadcast(intent);
    	  
    	    Toast.makeText(getApplicationContext(), "GPS Desativado", Toast.LENGTH_LONG).show();
    	    */
    		
    		Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);   
    	    startActivityForResult(intent, 1);
    	    Toast.makeText(getApplicationContext(), "Desative o GPS", Toast.LENGTH_LONG).show();
    	}
    }
    
    public LocationListener coletaDadosGps(){
		try{
			LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			LocationListener locationListener = new LocationListener() {
				public void onLocationChanged(Location location) {
					Double getLatitude = location.getLatitude();
					Double getLongitude = location.getLongitude();
					float getAccuracy = location.getAccuracy();
					
					// Referencia variavel da tela
					TextView latitude = (TextView) findViewById(R.id.latitude);
					TextView longitude = (TextView) findViewById(R.id.longitude);
					TextView accuracy = (TextView) findViewById(R.id.accuracy);
					
					// Atualizar campos da tela
					latitude.setText(getLatitude.toString());
					longitude.setText(getLongitude.toString());
					accuracy.setText(String.valueOf(getAccuracy));
					
					return ;
				}
				
				public void onStatusChanged(String provider, int status, Bundle extras) {
					//Toast.makeText(getApplicationContext(), "Status GPS alterado",Toast.LENGTH_SHORT).show();
				}
				public void onProviderDisabled(String provider) {
					Toast.makeText(getApplicationContext(),"GPS desligado pelo usuário",Toast.LENGTH_SHORT).show();
				}
				public void onProviderEnabled(String provider) {
					Toast.makeText(getApplicationContext(),"GPS ligado pelo usuário",Toast.LENGTH_SHORT).show();
				}
			};
			
     	    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
     	    return locationListener;
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(),	"Falha na consulta ao GPS", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		
		return null;
    }
    
    public void habilitaEndereco(String categoria){
    	DataHelper dh = new DataHelper(this);
    	String cadastraEndereco = dh.existeEndereco(categoria);
    	
    	if(cadastraEndereco.equals("1")){
    		EditText enderecoRua = (EditText) findViewById(R.id.enderecoRua);
    		EditText enderecoNumero = (EditText) findViewById(R.id.enderecoNumero);
    		EditText enderecoBairro = (EditText) findViewById(R.id.enderecoBairro);
    		EditText enderecoTelefone = (EditText) findViewById(R.id.enderecoTelefone);
    		
        	enderecoRua.setEnabled(true);
        	enderecoNumero.setEnabled(true);
        	enderecoBairro.setEnabled(true);
        	enderecoTelefone.setEnabled(true);
    	}else{
    		EditText enderecoRua = (EditText) findViewById(R.id.enderecoRua);
    		EditText enderecoNumero = (EditText) findViewById(R.id.enderecoNumero);
    		EditText enderecoBairro = (EditText) findViewById(R.id.enderecoBairro);
    		EditText enderecoTelefone = (EditText) findViewById(R.id.enderecoTelefone);
    		
        	enderecoRua.setEnabled(false);
        	enderecoNumero.setEnabled(false);
        	enderecoBairro.setEnabled(false);
        	enderecoTelefone.setEnabled(false);
    	}
    }
    
    public void carregaMenuCategoria(){
    	DataHelper dh = new DataHelper(this);
    	final Spinner categoria = (Spinner) findViewById(R.id.categoria);
    	
    	List<String> db_categoria = dh.menuCategoria();
        ArrayAdapter<String> db_adapter_categoria = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, db_categoria);
        db_adapter_categoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoria.setAdapter(db_adapter_categoria);
        
        categoria.setOnItemSelectedListener(new OnItemSelectedListener() {
        	public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) {
        		int item = categoria.getSelectedItemPosition();
        		habilitaEndereco(String.valueOf(item+1));
            }
            public void onNothingSelected(AdapterView<?> parent) {
              // Do nothing.
            }
        });
    }
    
    public void comporEmail(){
    	DataHelper dh = new DataHelper(this);
    	
    	// Referencia variavel da tela
		Spinner categoria = (Spinner) findViewById(R.id.categoria);
    	EditText nomePoi = (EditText) findViewById(R.id.nomePoi);
    	TextView latitude = (TextView) findViewById(R.id.latitude);
		TextView longitude = (TextView) findViewById(R.id.longitude);
		TextView accuracy = (TextView) findViewById(R.id.accuracy);
		EditText enderecoRua = (EditText) findViewById(R.id.enderecoRua);
    	EditText enderecoNumero = (EditText) findViewById(R.id.enderecoNumero);
    	EditText enderecoBairro = (EditText) findViewById(R.id.enderecoBairro);
    	EditText enderecoTelefone = (EditText) findViewById(R.id.enderecoTelefone);
    	String email = "pois@tracksource.org.br";
    	
		int filtroCategoria = categoria.getSelectedItemPosition() + 1;
		String categoriaCodigoSite = dh.categoriaCodigoSite(String.valueOf(filtroCategoria));
		
		String mensagem = "<categoria>" + categoriaCodigoSite + "</categoria>";
		mensagem += "\n<nomepoi>" + nomePoi.getText() + "</nomepoi>";
		mensagem += "\n<latitude>" + latitude.getText() + "</latitude>";
		mensagem += "\n<longitude>" + longitude.getText() + "</longitude>";
		mensagem += "\n<accuracy>" + accuracy.getText() + "</accuracy>";
		mensagem += "\n<endereco_rua>" + enderecoRua.getText() + "</endereco_rua>";
		mensagem += "\n<endereco_numero>" + enderecoNumero.getText() + "</endereco_numero>";
		mensagem += "\n<endereco_bairro>" + enderecoBairro.getText() + "</endereco_bairro>";
		mensagem += "\n<endereco_telefone>" + enderecoTelefone.getText() + "</endereco_telefone>";
		mensagem += "\n\nhttp://maps.google.com/?q=" + latitude.getText() + "," + longitude.getText();
		mensagem += "\n\nEnviado via Android";
    	
        Intent i = new Intent(Intent.ACTION_SEND);  
        //i.setType("text/plain"); //use this line for testing in the emulator  
        i.setType("message/rfc822") ; // use from live device
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{email});  
        i.putExtra(Intent.EXTRA_SUBJECT,"Tracksource - Novo POI");  
        i.putExtra(Intent.EXTRA_TEXT, mensagem);  
        startActivity(Intent.createChooser(i, "E-mail:"));
        
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.removeUpdates((LocationListener) locationListener);
        
    }
    
    @Override
    protected void onPause() {
    super.onPause();
     //saveDataFromCurrentState();
      LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
      locationManager.removeUpdates((LocationListener) locationListener);
      finish();
      //Toast.makeText(this, "onPause Poi", Toast.LENGTH_LONG).show();
    }
    
    /*
    @Override
    protected void onRestart() {
    super.onRestart();
    Toast.makeText(this, "onRestart Poi", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
    super.onResume();
    Toast.makeText(this, "onResume Poi", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
    // TODO Auto-generated method stub
    super.onStart();
    //updateFromSavedState();
    Toast.makeText(this, "onStart Poi", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
    // TODO Auto-generated method stub
    super.onDestroy();
    Toast.makeText(this, "onDestroy Poi", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
    // TODO Auto-generated method stub
    super.onStop();
    Toast.makeText(this, "onStop Poi", Toast.LENGTH_LONG).show();
    }
    */
}
