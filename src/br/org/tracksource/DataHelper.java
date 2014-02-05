package br.org.tracksource;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class DataHelper {

   private static final String DATABASE_NAME = "tracksource.db";
   private static final int DATABASE_VERSION = 1;
   private Context context;
   private SQLiteDatabase db;
 
   public DataHelper(Context context) {
      this.context = context;
      OpenHelper openHelper = new OpenHelper(this.context);
      this.db = openHelper.getWritableDatabase();
   }

   public void sql(String sql) {
	  this.db.execSQL(sql);
   }

   public void deleteAll(String TABLE_NAME) {
      this.db.delete(TABLE_NAME, null, null);
   }
   
   public void atualizaBancoDeDados() {
	   OpenHelper openHelper = new OpenHelper(this.context);
	   openHelper.atualizaBancoDeDados(db);
   }
   
   // ---------------------- //
   public String categoriaCodigoSite(String codigoCategoria) {
	   Cursor cursor = this.db.rawQuery("SELECT codigoSite FROM categoria WHERE codigo = " + codigoCategoria, null);
	   cursor.moveToFirst();
	   String codigoSite = cursor.getString(0);
	   cursor.moveToNext();
	   if(cursor != null && !cursor.isClosed()) {
		   cursor.close();
	   }
	   return codigoSite;
   }
   
   public String existeEndereco(String codigoCategoria) {
	   Cursor cursor = this.db.rawQuery("SELECT existeEndereco FROM categoria WHERE codigo = " + codigoCategoria, null);
	   cursor.moveToFirst();
	   String cadastraEndereco = cursor.getString(0);
	   cursor.moveToNext();
	   if(cursor != null && !cursor.isClosed()) {
		   cursor.close();
	   }
	   return cadastraEndereco;
   }
   
   public List<String> menuCategoria(){
   	  List<String> list = new ArrayList<String>();
	  
	  Cursor cursor = this.db.rawQuery("SELECT nome FROM categoria ORDER BY codigo", null);
	  if (cursor.moveToFirst()) {
	    do {
	      list.add(cursor.getString(0));
	    } while (cursor.moveToNext());
	  }
	  if (cursor != null && !cursor.isClosed()) {
	    cursor.close();
	  }
	  
	  return list;
   }
   
   // ---------------------- //
   
   private static class OpenHelper extends SQLiteOpenHelper {

      OpenHelper(Context context) {
         super(context, DATABASE_NAME, null, DATABASE_VERSION);
      }

      public void atualizaBancoDeDados(SQLiteDatabase db){
    	
    	db.delete("categoria", null, null);
    	
        Log.w("Tracksource", "Populando com dados as tabelas.");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('1','1','Academia de ginástica','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('2','2','Acampamento, Camping','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('3','3','Aeroporto, Aeródromo, Pista de pouso','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('4','4','Água potável, Bica, Fonte','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('5','131','Apoio em rodovias (socorro mecânico)','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('6','7','Área de mergulho','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('7','8','Área de natação natural e pública (exceto clube)','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('8','9','Área de perigo','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('9','10','Área de pesca, Pesque pague, Rio','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('10','11','Área de piquenique','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('11','12','Área militar, Forte, Quartel','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('12','14','Assistente de navegação (Amarelo)','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('13','15','Assistente de navegação (Azul)','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('14','16','Assistente de navegação (Branco)','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('15','17','Assistente de navegação (Laranja)','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('16','18','Assistente de navegação (Preto)','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('17','19','Assistente de navegação (Verde)','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('18','20','Assistente de navegação (Verde,Vermelho)','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('19','21','Assistente de navegação (Vermelho)','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('20','22','Assistente de navegação Desligado (Branco)','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('21','23','Assistente de navegação desligado (Preto)','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('22','95','Atração turística, Mirante, Vista','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('23','24','Balança para pesagem de caminhões','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('24','100','Balsa, Ferry boat','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('25','25','Banco, Caixa automático, Banco Postal, Lotérica','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('26','26','Banheiro em rodovia','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('27','5','Banheiro público','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('28','27','Bar, Boate, Adega, etc','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('29','29','Biblioteca','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('30','30','Bóia branca','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('31','31','Boliche','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('32','32','Bombeiros','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('33','33','Buzina de nevoeiro','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('34','34','Cachoeira','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('35','35','Cadeia de montanhas, Região geográfica, etc.','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('36','36','Campo de golfe','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('37','37','Campo de petróleo','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('38','38','Capital ou Distrito Federal','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('39','39','Cemitério, Velório e Funerárias','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('40','40','Centro de convenções','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('41','128','Centros comunitários','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('42','41','Cidade com até 25 mil habitantes','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('43','44','Cidade com mais de 100 mil habitantes','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('44','42','Cidade entre 25 e 100 mil habitantes','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('45','43','Cidade histórica ou fantasma','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('46','45','Cinema','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('47','46','Clube; Hípica; Associações Esportivas Privadas','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('48','137','Comentários do Desenvolvedor (não aparecerão no mapa compilado)','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('49','47','Correios','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('50','48','Cruzamento, Desvio, Rotatória','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('51','129','Distrito, Vila, Povoado, Bairro, etc','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('52','51','Escola, Universidade, etc.','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('53','52','Esqui ou Esportes aquáticos','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('54','53','Estação de rádio ou TV','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('55','54','Estacionamento','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('56','55','Estádio, Campo, Arena, Hipódromo; Complexo esportivo','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('57','56','Extremo de trilha','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('58','57','Farmácia, Drogaria','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('59','58','Floresta','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('60','59','Heliponto','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('61','60','Hospital, Pronto-Socorro, Posto Médico, Salva-Vida','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('62','61','Hotel, Pousada, Albergue, Motel','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('63','62','Igreja, Templo','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('64','130','Ilha','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('65','64','Informações em rodovias','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('66','63','Informações turísticas','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('67','134','Lagoa','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('68','65','Lanchonete','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('69','66','Lava rápido','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('70','67','Locadora de veículos','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('71','69','Loja - Artigos de casa e jardim','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('72','71','Loja - Decoração','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('73','72','Loja - Departamentos','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('74','75','Loja - Especializada','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('75','73','Loja - Informática','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('76','68','Loja - Outros tipos','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('77','74','Loja - Roupas e acessórios','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('78','138','Lombada Rodoviária (Alerta 200m)','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('79','136','Lombada Urbana (Alerta 50m)','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('80','76','Marina','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('81','77','Mercearia','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('82','78','Mina','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('83','80','Museu','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('84','81','Naufrágio','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('85','82','Obstrução na superfície','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('86','83','Obstrução submersa','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('87','84','Obstrução visível','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('88','85','Oficina, Borracharia, Troca de óleo, Serviço em veículo','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('89','126','Órgão público do executivo, Prefeitura, Palácio federal e estadual, etc','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('90','127','Órgão público do judiciário, Fórum, Tribunal, Comarca judicial, etc','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('91','98','Órgão público do legislativo e outros, Assembléia, Câmara, Senado, Congresso, Embaixada, etc','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('92','86','Padaria','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('93','87','Parque de diversão','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('94','88','Parque, Jardim, Praça','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('95','89','Pedágio','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('96','90','Pedra na superfície','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('97','91','Pedra submersa','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('98','79','Pico, Montanha, Morro','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('99','133','Placa de km em rodovia, Marco','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('100','49','Polícia Civil, Militar, Federal, Presidio, etc','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('101','93','Polícia rodoviária','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('102','94','Ponte, Viaduto','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('103','13','Portaria, Portão, Porteira, Tronqueira, Colchete','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('104','135','Porto','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('105','96','Posto de Gasolina','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('106','70','Posto de Gasolina com Loja de Conveniência','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('107','97','Praia','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('108','50','Prédio, Indústria, Contruções e Imóveis em geral','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('109','99','Radar, Semáforos com registro de avanço e/ou velocidade, dispositivos de registro eletrônico de infrações','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('110','101','Represa, Usina Hidrelétrica','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('111','28','Reservatório, Dique, Açude','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('112','102','Restaurante - Alemão','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('113','103','Restaurante - Americano','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('114','104','Restaurante - Asiático (Japonês, Tailandês, Indiano, etc.)','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('115','105','Restaurante - Cafeteria','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('116','109','Restaurante - Carnes','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('117','106','Restaurante - Chinês','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('118','107','Restaurante - Churrascaria','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('119','110','Restaurante - Francês','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('120','111','Restaurante - Frutos do Mar','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('121','108','Restaurante - Internacional','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('122','112','Restaurante - Italiano','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('123','113','Restaurante - Mexicano','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('124','114','Restaurante - Outros Tipos, Buffet (Salão de Festas)','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('125','92','Restaurante - Pizzaria','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('126','115','Revenda de Veículos, Lojas de Peças e Acessórios','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('127','116','Rocha, pedra, paredão, gruta','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('128','122','Rodoviária, Ferroviária, Metrô, Ponto de Táxi, Ponto de Ônibus','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('129','117','Saída de Rodovia','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('130','118','Shopping Center','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('131','119','Supermercado','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('132','120','Teatro','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('133','121','Telefone público, Posto Telefônico','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('134','123','Torre de Telecomunicações, Transmissão de Energia, Antenas, etc','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('135','124','Túnel','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('136','139','Viaduto Assinalado (Não será compilado no mapa)','0')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('137','132','Wireless, Hot Spot','1')");
        db.execSQL("INSERT INTO categoria (codigo,codigoSite,nome,existeEndereco) VALUES ('138','125','Zoológico, Aquário','1')");

      }

      @Override
      public void onCreate(SQLiteDatabase db) {
    	 Log.w("Tracksource", "Criando tabelas.");
         db.execSQL("CREATE TABLE categoria (codigo INTEGER PRIMARY KEY, nome TEXT, existeEndereco BOOL, codigoSite INTEGER)");
         this.atualizaBancoDeDados(db);
      }

      @Override
      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         Log.w("Tracksource", "Apagando tabelas.");
         db.execSQL("DROP TABLE IF EXISTS categoria");
         onCreate(db);
      }
   }
}