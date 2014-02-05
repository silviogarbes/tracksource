package br.org.tracksource;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Configuracoes extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracao);
		// TODO Put your code here
        
        Button botaoSobre = (Button) findViewById(R.id.botaoSobre);
        botaoSobre.setOnClickListener(new Button.OnClickListener() {
	        public void onClick(View v){
	        	Toast.makeText(getApplicationContext(), "Tracksource \n 2012 \n \n Aplicativo desenvolvido por Silvio Garbes! \n silviogarbes@gmail.com \n \n www.tracksource.org.br", Toast.LENGTH_LONG).show();


	        	/*
	        	AlertDialog.Builder sobre = new AlertDialog.Builder(getApplicationContext());
	        	sobre.setTitle("Sobre");
	        	sobre.setMessage("Tracksource \n 2012 \n \n Aplicativo desenvolvido por Silvio Garbes! \n silviogarbes@gmail.com \n \n www.tracksource.org.br"); 
	            sobre.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	            
	            //AlertDialog alert = sobre.create();
	            //alert.show();
	            	
	            
	            	
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						
					}});
	        	*/
        }});
        
        Button botaoAtualizarMenu = (Button) findViewById(R.id.botaoAtualizarMenu);
        botaoAtualizarMenu.setOnClickListener(new Button.OnClickListener() {
	        public void onClick(View v){
	        	DataHelper dh = new DataHelper(getApplicationContext());
	        	Toast.makeText(getApplicationContext(), "Iniciando atualização", Toast.LENGTH_LONG).show();
	            dh.atualizaBancoDeDados();
	            Toast.makeText(getApplicationContext(), "Atualizado", Toast.LENGTH_LONG).show();
        }});
        
        
	}
	
	
	@Override
    protected void onPause() {
      super.onPause();
      //saveDataFromCurrentState();
      finish();
      //Toast.makeText(this, "onPause Configuracoes", Toast.LENGTH_LONG).show();
    }

	/*
    @Override
    protected void onRestart() {
    super.onRestart();
    Toast.makeText(this, "onRestart Configuracoes", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
    super.onResume();
    Toast.makeText(this, "onResume Configuracoes", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
    // TODO Auto-generated method stub
    super.onStart();
    //updateFromSavedState();
    Toast.makeText(this, "onStart Configuracoes", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
    // TODO Auto-generated method stub
    super.onDestroy();
    Toast.makeText(this, "onDestroy Configuracoes", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
    // TODO Auto-generated method stub
    super.onStop();
    Toast.makeText(this, "onStop Configuracoes", Toast.LENGTH_LONG).show();
    }
    */
	
}
