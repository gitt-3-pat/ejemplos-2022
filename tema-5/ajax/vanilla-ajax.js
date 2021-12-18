
//objeto XMLHttpRequest
var request = new XMLHttpRequest();
//funcion de callback asignada al cambio del estado de la petición
request.onreadystatechange = function() {
  //estado == 4 quiere decir que se ha completado
  if(request.readyState === 4) {
    //comprobamos el código de la respuesta HTTP
    if(request.status === 200) {
      document.getElementById("response-div").innerHTML = "Response from the server: " + request.responseText;
    } else {
      document.getElementById("error-div").innerHTML = 'An error occurred during your request: ' +  request.status + ' ' + request.statusText;
    }
  }
}
//Tipo de método HTTP y URL de la petición AJAX
request.open('GET', '/api/status');
//Lanzamos la petición AJAX
request.send();
