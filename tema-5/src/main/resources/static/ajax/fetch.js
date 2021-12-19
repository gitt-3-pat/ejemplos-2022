
var movieSearch = {}:
//GET
funtion fetchMovieSearch(){
  fetch('http://www.omdbapi.com/?apikey=cc1014ca&s=Star+Wars',
  {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
    }
  })
  //Suscribimos a la promesa Response
  .then(res => {
    if(res.ok){
      //Si la respuesta se resolvió bien, procedemos a resolver la promesa Body
      return res.json()
    }else{
      throw res;
    }
  })
  //Suscribimos a la promesa Body
  .then(r => {
    //Una vez resuelta la última promesa, asignamos el valor de la respuesta a una variable JSON
    movieSearch=r
  })
  //Errores de RED y respuestas KO
  .catch(e => console.log(e))
}

var movie = {"Title":"Star Wars: Episode IV - A New Hope","Year":"1977","imdbID":"tt0076759","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BNzVlY2MwMjktM2E4OS00Y2Y3LWE3ZjctYzhkZGM3YzA1ZWM2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg"}
//POST
funtion fetchMovieSearch(){
  fetch('http://www.omdbapi.com/?apikey=cc1014ca&s=Star+Wars',
  {
    method: 'POST',
    body: JSON.stringify(movie)
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
    }
  })
  //Suscribimos a la promesa Response
  .then(res => {
    if(res.ok){
      //Si la respuesta se resolvió bien, procedemos a resolver la promesa Body
      return res.json()
    }else{
      throw res;
    }
  })
  //Suscribimos a la promesa Body
  .then(r => {
    //Una vez resuelta la última promesa, asignamos el valor de la respuesta a una variable JSON
    movieSearch=r
  })
  //Errores de RED y respuestas KO
  .catch(e => console.log(e))
}


var formData = new FormData();
var dni = document.querySelector("input[type='file']");

formData.append('dni-text', '000044444N');
formData.append('dni', dni.files[0]);
//POST con imagen form-data
fetch('https://localhost:8085/microservice/alta-dni', {
  method: 'POST',
  headers: {
    'Content-Type': 'multipart/form-data',
    'Accept': 'text/plain',
  },
  body: formData
})
.then(response => response.json())
.then(response => console.log("DNI entry OK"))
.catch(error => console.error('Error:', error))
