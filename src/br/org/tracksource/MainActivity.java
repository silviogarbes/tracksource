package br.org.tracksource;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) { 	
    	requestWindowFeature(Window.FEATURE_LEFT_ICON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Adicionar icone na barra de titulo
        setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.icon_tracksource);
        
        
        Button botaoPoi = (Button) findViewById(R.id.botaoPoi);
        botaoPoi.setOnClickListener(new Button.OnClickListener() {
	        public void onClick(View v){
	        	Intent intent = new Intent(MainActivity.this,Poi.class);
	        	startActivity(intent);
        }});
      
        Button botaoGravarTrilha = (Button) findViewById(R.id.botaoGravarTrilha);
        botaoGravarTrilha.setOnClickListener(new Button.OnClickListener() {
	        public void onClick(View v){
	        	Toast.makeText(getApplicationContext(), "Envie para o desenvolvedor a trilha no formato gpx", Toast.LENGTH_LONG).show();
	        	
	        	Intent intent = new Intent(Intent.ACTION_VIEW);
	        	intent.setData(Uri.parse("market://details?id=com.google.android.maps.mytracks"));
	        	startActivity(intent);	        	
        }});
        
        Button botaoGpsNavitel = (Button) findViewById(R.id.botaoGpsNavitel);
        botaoGpsNavitel.setOnClickListener(new Button.OnClickListener() {
	        public void onClick(View v){
	        	Intent intent = new Intent(Intent.ACTION_VIEW);
	        	intent.setData(Uri.parse("market://details?id=com.navitel"));
	        	startActivity(intent);
        }});
        
        Button botaoConfiguracoes = (Button) findViewById(R.id.botaoConfiguracoes);
        botaoConfiguracoes.setOnClickListener(new Button.OnClickListener() {
	        public void onClick(View v){
	        	Intent intent = new Intent(MainActivity.this,Configuracoes.class);
	        	startActivity(intent);
        }});
    }
    
    /*
    @Override
    protected void onPause() {
    super.onPause();
     //saveDataFromCurrentState();
      //finish();
      Toast.makeText(this, "onPause MainActivity", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
    super.onRestart();
    Toast.makeText(this, "onRestart MainActivity", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
    super.onResume();
    Toast.makeText(this, "onResume MainActivity", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
    // TODO Auto-generated method stub
    super.onStart();
    //updateFromSavedState();
    Toast.makeText(this, "onStart MainActivity", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
    // TODO Auto-generated method stub
      super.onDestroy();
      Toast.makeText(this, "onDestroy MainActivity", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
    // TODO Auto-generated method stub
    super.onStop();
    Toast.makeText(this, "onStop MainActivity", Toast.LENGTH_LONG).show();
    }
    */
    
    
    
}