<?php
require 'config.php';

$societe = $_POST['societe'];
$civilite = $_POST['civilite'];
$nom = $_POST['nom'];
$prenom = $_POST['prenom'];
$adresse = $_POST['adresse'];
$code_postal = $_POST['code_postal'];
$ville = $_POST['ville'];
$pays = $_POST['pays'];
$telephone = $_POST['telephone'];
$mobile = $_POST['mobile'];
$fax = $_POST['fax'];
$email = $_POST['email'];
$type = $_POST['type'];
$livre_meme_adresse = $_POST['livre_meme_adresse'];
$facture_meme_adresse = $_POST['facture_meme_adresse'];
$exempt_tva = $_POST['exempt_tva'];
$observations = $_POST['observations'];

$sql = "INSERT INTO client(Societe, Civilite, NomClient, Prenom, Adresse, CodePostal, Ville, Pays, Telephone, Mobile, Fax, Email, Type, LivreMemeAdresse, FactureMemeAdresse, ExemptTva, SaisiPar, SaisiLe, Observations) VALUES ('" 
. $societe . "', '" . $civilite . "', '" . $nom . "', '" . $prenom . "', '" . $adresse . "', '" . $code_postal . "', '" . $ville. "', '" . $pays. "', '" . $telephone . "', '" . $mobile . "', '" . $fax . "', '" . $email . "', " . $type . ", " . $livre_meme_adresse . ", " . $facture_meme_adresse . ", " . $exempt_tva . ", 'client', '" . date('Y-m-d') . "', '" . $observations . "');";

$result = mysqli_query($con, $sql);

if($result) {
	$code = "reg_success";
	$message = "Merci, vous avez ajouter un nouveau client";
} else {
	$code = "reg_failed";
	$message = "Erreur dans l'ajouter de client";
}

$response = array();
array_push($response, array("code" => $code, "message" => $message));
echo json_encode($response);