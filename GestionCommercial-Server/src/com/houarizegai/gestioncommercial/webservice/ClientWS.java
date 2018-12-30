package com.houarizegai.gestioncommercial.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.houarizegai.gestioncommercial.database.dao.ClientDao;
import com.houarizegai.gestioncommercial.database.models.Client;
import com.houarizegai.gestioncommercial.database.models.designpatterns.builder.ClientBuilder;

@Path("/client")
public class ClientWS {

	public ClientWS() {
		
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayMyName(@QueryParam("name") String myName) {
		return "Hello " + myName + " !";
	}
	
	@Path("/add")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject addClient(@QueryParam("societe") String societe, @QueryParam("civilite") String civilite, @QueryParam("nom") String nomClient, 
			@QueryParam("prenom") String prenom, @QueryParam("adresse") String adresse, @QueryParam("codepostal") String codePostal, @QueryParam("ville") String ville,
			@QueryParam("pays") String pays, @QueryParam("tel") String telephone, @QueryParam("mob") String mobile, @QueryParam("fax") String fax, 
			@QueryParam("email") String email, @QueryParam("type") int type, @QueryParam("livremadr") boolean livreMemeAdresse, 
			@QueryParam("facmadr") boolean factureMemeAdresse, @QueryParam("exempttva") boolean exemptTva, @QueryParam("obs") String observations) {
		
		JSONObject rootJSON = new JSONObject();
		
		if(nomClient == null) {
			rootJSON.append("status", "failed");
			rootJSON.append("message", "the last name of client is empty !");
			return rootJSON;
		}
		if(prenom == null) {
			rootJSON.append("status", "failed");
			rootJSON.append("message", "the first name of client is empty !");
			return rootJSON;
		}
		
		Client client = new ClientBuilder()
				.setSociete(societe)
                .setCivilite(civilite)
                .setNomClient(nomClient)
                .setPrenom(prenom)
                .setTelephone(telephone)
                .setMobile(mobile)
                .setFax(fax)
                .setEmail(email)
                .setType(type)
                .setAdresse(adresse)
                .setCodePostal(codePostal)
                .setVille(ville)
                .setPays(pays)
                .setLivreMemeAdresse(livreMemeAdresse)
                .setFactureMemeAdresse(factureMemeAdresse)
                .setExemptTva(exemptTva)
                .setSaisiPar("client")
                .setSaisiLe(new java.util.Date())
                .setAuteurModif(null)
                .setDateModif(null)
                .setObservations(observations)
				.build();
		
		int status = ClientDao.addClient(client);

        switch (status) {
            case -1:
            	rootJSON.append("status", "failed");
            	rootJSON.append("message", "erreur de connexion !");
                break;
            case 0:
            	rootJSON.append("status", "failed");
            	rootJSON.append("message", "erreur dans l'ajoute de client !");
                break;
            default:
            	rootJSON.append("status", "success");
            	rootJSON.append("message", "vous avez ajouter un client !");
            	break;
        }
        
        return rootJSON;
	}

}
