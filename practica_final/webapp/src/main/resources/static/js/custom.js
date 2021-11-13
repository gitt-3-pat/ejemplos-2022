$( document ).ready(function() {
	
	onReadyInitialize();
});


function onReadyInitialize(){
	let minHeight = 570;
	$('.img-list').each(function(i,item){
		if(item.clientHeight < minHeight && item.clientHeight > 300 )
			minHeight = item.clientHeight;
	})
	$('.img-list').each(function(i,item){
		item.style.maxHeight = minHeight + 'px';
	})
	initListeners();
}
var brands = "";
var cuts = "";
var sleeves = ""; 
var sizes="";

function initListeners(){
	$('#register-btn').off().on('click', function(e){
		e.preventDefault();
		location.href = '/register';
	})
	
	$('#checkbox-isVendor').off().on('change', function(e){
		$('#isVendor').val($('#checkbox-isVendor').is(':checked'));
	})
	$('#checkbox-isBuyer').off().on('change', function(e){
		$('#isBuyer').val($('#checkbox-isBuyer').is(':checked'));
	})
	$('#item-type').off().on('change', function(e){
		let value = e.target.value;
		if(value === 'VESTIDO'){
			$('.vestido').removeClass('hide')
			$('.vestido-item').prop('required',true);
		}else if(value === 'VELO'){
			$('.vestido').addClass('hide')
			$('.vestido-item').removeAttr('required');
		}else{
			$('.vestido').addClass('hide')
			$('.vestido-item').removeAttr('required');
		}
	})
	
	$('.remove-wishlist').off().on('click', function(e){
		e.preventDefault();
		let itemId = e.currentTarget.attributes["data-id"].value;
		let shouldRefreshMainWishlist = e.currentTarget.attributes["data-main-wishlist"] != undefined;
		removeItemFromWishlist(itemId, shouldRefreshMainWishlist);
	})
	
	$('.add-wishlist').off().on('click', function(e){
		e.preventDefault();
		let itemId = e.currentTarget.attributes["data-id"].value;
		addItemToWishlist(itemId);
	})
	$('#btn-send-contact-vendor').off().on('click', function(e){
		contactVendor();
	})
	
	$('#btn-main-contact').off().on('click', function(e){
		mainContact();
	})
	
	
	
	$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})
	
	if(document.getElementById('drop_zone') != null)
		initDragAndDrop();
	initAccordions();
	initSliders();
	
	$('.inner > a').off().on('click', function(e){
		if(e.currentTarget.className == ""){
			e.currentTarget.classList.add('active')
			e.currentTarget.getElementsByTagName("span")[0].classList.add('filter-on')
			
		}else{
			e.currentTarget.classList.remove('active')
			e.currentTarget.getElementsByTagName("span")[0].classList.remove('filter-on')
		}
	})
	$('.tagcloud > a').off().on('click', function(e){
		if(e.currentTarget.className == ""){
			e.currentTarget.classList.add('active')
			e.currentTarget.getElementsByTagName("span")[0].classList.add('filter-on')
			
		}else{
			e.currentTarget.classList.remove('active')
			e.currentTarget.getElementsByTagName("span")[0].classList.remove('filter-on')
		}
	})
	
	$('.btn-filter').off().on('click', function(){
		processFilters();
	})
	$('.btn-clear-filter').off().on('click', function(){
		clearFilters();
	})
	
	initFilters();
	$('#order-by').off().on('change', function(){
		changeOrderBy();
	})
	
	$('.btn-delete-product-pre').off().on('click', function(e){
		e.preventDefault();
		let id = e.target.dataset.id
		deleteProduct(id)
	});
	
	$('.btn-accept-pre').off().on('click', function(e){
		e.preventDefault();
		let id = e.target.dataset.id
		acceptProduct(id)
	});
	
	$('.btn-decline-pre').off().on('click', function(e){
		e.preventDefault();
		let id = e.target.dataset.id
		declineProduct(id)
	});
	
	$('#register-user-form').on('submit', function(e){
		if($('#checkbox-legal-1').is(':checked') === false){
			e.preventDefault();
			$('#checkbox-legal-1').css('box-shadow', '0px 0px 0px 1px rgba(255,0,0,1)');
		}
		if($('#password').val() !== $('#password-repeat').val()){
			e.preventDefault();
			$('#password-mismatch').removeClass('hide');
		}else{
			$('#password-mismatch').addClass('hide');
		}
	})
	$('#login-form').on('submit', function(e){
		if($('#email').val() === ''){
			e.preventDefault();
			$('#email').css('border','1px red solid');
		}
		if($('#password').val() === ''){
			e.preventDefault();
			$('#password').css('border','1px red solid');
		}
		
	});
	
	$('#modify-user-form').on('submit', function(e){
		if($('#new-password').val() !== '' && $('#new-password').val() !== $('#new-password-repeat').val()){
			e.preventDefault();
			$('#password-mismatch').removeClass('hide');
		}else if($('#new-password').val() !== ''){
			$('#password-mismatch').addClass('hide');
			$('#modifyPassword').val("true");
		}else{
			$('#password-mismatch').addClass('hide');
		}
	})
	
	if(typeof $('#item-type').val() !== 'undefined'){
		let value = $('#item-type').val();
		if(value === 'VESTIDO'){
			$('.vestido').removeClass('hide')
			$('.vestido-item').prop('required',true);
			$('.velo').removeClass('hide');
			$('.velo-item').prop('required',true);
		}else if(value === 'VELO'){
			$('.vestido').addClass('hide')
			$('.vestido-item').removeAttr('required');
			$('.velo-item').removeAttr('required');
			$('.velo').addClass('hide')
		}else{
			$('.velo-item').prop('required',true);
			$('.vestido').addClass('hide')
			$('.vestido-item').removeAttr('required');
			$('.velo').removeClass('hide')
		}
	}
	setTimeout(function(){$('#new-password').val('')}, 1000);
	
	$('.btn-buyout').off().on('click', function(e){
		e.preventDefault();
		let id = e.target.dataset.id
		location.href = `${baseURL}checkout/${id}`;
	})
	
	initCookies();
	checkMessagePopup();
}

function checkMessagePopup(){
	if(message !== ''){
		$.magnificPopup.open({
	        items: {
	          src: '<div class="white-popup">'+
	          			'<div class="kt-popup-newsletter">'+
	          				'<div class="popup-title">'+
	          					`<h5 style="margin-bottom:50px;text-transform: initial;">${message}</h5>`+
	          				'</div>'+
	          			'</div>'+
	          		'</div>',
	          type: 'inline'
	        }
	      });
	}
}
function initCookies(){
	if(document.cookie.indexOf("cookie-accepted") == -1){
		$.magnificPopup.open({
	        items: {
	          src: '<div class="white-popup">'+
	          			'<div class="kt-popup-newsletter">'+
	          				'<div class="popup-title">'+
	          					'<h5 style="margin-bottom:30px;text-transform: initial;"><b>Política de cookies</b></h5>'+
	          					`<h6 style="margin-bottom:30px;text-transform: initial;">En este sitio web hacemos uso de cookies propias para identificar a los usuarios y corroborar que han aceptado el uso de cookies.</h6>`+
	          					`<h6 style="margin-bottom:30px;text-transform: initial;">Para más información sobre las cookies que utilizamos pinche <a target="_blank" href="${baseURL}cookies.pdf">AQUÍ</a></h6>`+
	          					`<h6 style="margin-bottom:50px;text-transform: initial;">Puede aceptar el uso de dichas cookies pulsando ACEPTAR y continuar con la navegación</h6>`+
	          					`<a onClick="window.history.back()" style="float:left" class="button" href="#">Denegar</a><a style="float:right" onClick="acceptCookies()" class="button" href="#">Aceptar</a>`+
	          				'</div>'+
	          			'</div>'+
	          		'</div>',
	          type: 'inline'
	        }
	      });
	}
}
function acceptCookies(){
	document.cookie = "cookie-accepted=true;max-age=63072000";
	location.reload()
}
function deleteProduct(id){
	console.log(id)
	$.magnificPopup.open({
        items: {
          src: '<div class="white-popup">'+
          			'<div class="kt-popup-newsletter">'+
          				'<div class="popup-title">'+
          					'<h3>¡Atención!</h3>'+
          					`<p class="notice">¿Seguro que quiere eliminar este anuncio?</p><br/>`+
          					`<a onClick="doDeleteProduct(${id})" class="button" href="#">Eliminar</a>`+
          				'</div>'+
          			'</div>'+
          		'</div>',
          type: 'inline'
        }
      });
}

function acceptProduct(id){
	console.log(id)
	$.magnificPopup.open({
        items: {
          src: '<div class="white-popup">'+
          			'<div class="kt-popup-newsletter">'+
          				'<div class="popup-title">'+
          					'<h3>Aceptar la venta</h3>'+
          					`<p class="notice">Si dispone del producto acepte la venta para que continúe el proceso</p><br/>`+
          					`<a onClick="doAcceptProduct(${id})" class="button" href="#">Aceptar</a>`+
          				'</div>'+
          			'</div>'+
          		'</div>',
          type: 'inline'
        }
      });
}

function declineProduct(id){
	console.log(id)
	$.magnificPopup.open({
        items: {
          src: '<div class="white-popup">'+
          			'<div class="kt-popup-newsletter">'+
          				'<div class="popup-title">'+
          					'<h3>Rechazar la venta</h3>'+
          					`<p class="notice">Si no dispone ya del producto, por favor, rechaze la venta.</p><br/>`+
          					`<a onClick="doDeclineProduct(${id})" class="button" href="#">Rechazar</a>`+
          				'</div>'+
          			'</div>'+
          		'</div>',
          type: 'inline'
        }
      });
}

function doDeleteProduct(id){
	console.log("delete");
	fetch(`${baseURL}products/${id}`,
			{
				method: 'DELETE',
				headers: {'Content-Type':'application/json'}
			}
		)
		.then(r => {
			if(r.ok)
				location.reload();
		})
		.then(r => {
			location.reload();
		}).catch(e =>{
			location.reload();
		})
	
}
function doAcceptProduct(id){
	console.log("accept order");
	fetch(`${baseURL}products/${id}/accept`,
			{
				method: 'POST',
				headers: {'Content-Type':'application/json'}
			}
		)
		.then(r => {
			if(r.ok)
				location.reload();
		})
		.then(r => {
			location.reload();
		}).catch(e =>{
			location.reload();
		})
	
}
function doDeclineProduct(id){
	console.log("decline order");
	fetch(`${baseURL}products/${id}/decline`,
			{
				method: 'POST',
				headers: {'Content-Type':'application/json'}
			}
		)
		.then(r => {
			if(r.ok)
				location.reload();
		})
		.then(r => {
			location.reload();
		}).catch(e =>{
			location.reload();
		})
	
}
function initFilters(){
	const queryString = window.location.search;
	let urlParams = new URLSearchParams(queryString);
	const spans = document.querySelectorAll(`[data-filter*="filter"]`)
	for(var value of urlParams.values()) {
	  spans.forEach(function(s){
		  if(value.split(',').indexOf(s.innerHTML) !== -1){
			  s.classList.add('filter-on')
			  s.closest('a').classList.add('active')
		  }
	  })
	}
	//PAGES HREF
	urlParams.delete("page")
	urlParams.delete("size")
	if(urlParams.toString() !== ''){
		$('.page-ref').each(function(i,item){
			if(item.href.indexOf('?') !== -1)
				item.href = item.href + '&' + urlParams.toString();
			else
				item.href = item.href + '?' + urlParams.toString();
		})
	}
	if(urlParams.has('orderBy')){
		$('#order-by').val(urlParams.get('orderBy'));
		$('#order-by').chosen('destroy');
		$('#order-by').chosen();
		
	}
}

function clearFilters(){
	const queryString = window.location.search;
	let urlParams = new URLSearchParams(queryString);
	urlParams.delete('brands');
	urlParams.delete('sizes');
	urlParams.delete('sleeves');
	urlParams.delete('cuts');
	urlParams.delete('minHeight');
	urlParams.delete('maxHeight');
	urlParams.delete('minWeight');
	urlParams.delete('maxWeight');
	if(urlParams.toString() === '')
		location.href = location.protocol + '//' + location.host + location.pathname;
	else
		location.href = location.protocol + '//' + location.host + location.pathname + '?' + urlParams.toString();
	
}
function processFilters(){

	$('.filter-on').each(function(i, element){
		let filterType = element.dataset.filter;
		let value = element.innerHTML;
		console.debug('Filter of type ' + filterType + ' with value ' + value);
		switch (filterType) {
		case 'brand-filter':
			if(brands === '')
				brands+=value;
			else
				brands+=`,${value}`
			break;
		case 'sleeves-filter':
			if(sleeves === '')
				sleeves+=value;
			else
				sleeves+=`,${value}`
			break;
		case 'size-filter':
			if(sizes === '')
				sizes+=value;
			else
				sizes+=`,${value}`
			break;
		case 'cut-filter':
			if(cuts === '')
				cuts+=value;
			else
				cuts+=`,${value}`
			break;
		default:
			break;
		}
	});

	let finalUrl = getFilterUrl();
	location.href = finalUrl;
}

function getFilterUrl(){
	const queryString = window.location.search;
	let urlParams = new URLSearchParams(queryString);
	
	urlParams.delete('brands');
	urlParams.delete('sizes');
	urlParams.delete('sleeves');
	urlParams.delete('cuts');
	urlParams.delete('minHeight');
	urlParams.delete('maxHeight');
	urlParams.delete('minWeight');
	urlParams.delete('maxWeight');
	
	if(brands !== '')
		urlParams.set('brands', brands);
	if(cuts !== '')
		urlParams.set('cuts', cuts);
	if(sleeves !== '')
		urlParams.set('sleeves', sleeves);
	if(sizes !== '')
		urlParams.set('sizes', sizes);
	urlParams.set('minPrice',$('.slider-range-price').slider("values")[0])
	urlParams.set('maxPrice',$('.slider-range-price').slider("values")[1])
	if(urlParams.has('page')){
		urlParams.set('page',0);
	}
	let currentUrl = location.protocol + '//' + location.host + location.pathname + '?';
	return currentUrl + urlParams.toString();
	
}

function changeOrderBy(){
	if($('#order-by').val() !== ''){
		const queryString = window.location.search;
		let urlParams = new URLSearchParams(queryString);
		urlParams.set("orderBy",$('#order-by').val());
		if(urlParams.has('page')){
			urlParams.set('page',0);
		}
		let currentUrl = location.protocol + '//' + location.host + location.pathname + '?';
		location.href = currentUrl + urlParams.toString();
	}
}

function initSliders(){
	const queryString = window.location.search;
	let urlParams = new URLSearchParams(queryString);
	 $('.slider-range-price').each(function(){
         var min             = $(this).data('min');
         var max             = $(this).data('max');
         var unit            = $(this).data('unit');
         var value_min       = urlParams.get('minPrice') != null ? urlParams.get('minPrice'):$(this).data('value-min');
         var value_max       = urlParams.get('maxPrice') != null ? urlParams.get('maxPrice'):$(this).data('value-max');
         var label_result   = $(this).data('label-result');
         var t               = $(this);
         $( this ).slider({
           range: true,
           min: min,
           max: max,
           values: [ value_min, value_max ],
           slide: function( event, ui ) {
             var result = label_result +": "+  ui.values[ 0 ] +unit +' - '+ui.values[ 1 ]+ unit ;
             t.closest('.weight_slider_wrapper').find('.amount-range-price').html(result);
           }         
         });
         const r = label_result +": "+ value_min +unit +' - '+value_max+ unit ;
         t.closest('.weight_slider_wrapper').find('.amount-range-price').html(r);
     })
     
}

function initAccordions(){
	var acc = document.getElementsByClassName("accordion");
	var i;

	for (i = 0; i < acc.length; i++) {
	  acc[i].addEventListener("click", function() {
	    /* Toggle between adding and removing the "active" class,
	    to highlight the button that controls the panel */
	    this.classList.toggle("active");

	    /* Toggle between hiding and showing the active panel */
	    var panel = this.nextElementSibling;
	    if (panel.style.display === "block") {
	      panel.style.display = "none";
	    } else {
	      panel.style.display = "block";
	    }
	  });
	}
	
}
function nextTemplate(url) {
	
	document.location.href = url;
	
    /*$.get(url).done(function(fragment) { 
        $("#elements-container").replaceWith(fragment); 
        setTimeout(onReadyInitialize, 50);
    });*/
}



function addItemToWishlist(itemId, refreshMainWishlist){

	
	fetch(urlWishlist + "/" + itemId + "/add", 
		{
			method: 'POST',
			body: "token"
		}
	)
	.then(r => 	{
		if(!r.redirected)
		  return r.text()
			 else
			  location.href = baseURL + 'login'
	})
	.then(html => {
		$(".wishlist-menu").replaceWith(html);
		initListeners();
		if(refreshMainWishlist){
			 $.get(urlWishlist + "/refresh").done(function(fragment) { // get from controller
			     $("#wishlist-body").replaceWith(fragment); // update snippet of page
			     initListeners();
			 });
		}
	})
	.catch(e => console.log(e))
	
}
function removeItemFromWishlist(itemId, refreshMainWishlist){
	fetch(`${urlWishlist}/${itemId}/remove`, 
		{
			method: 'POST',
			body: "token"
		}
	)
	.then(r => {
		if(!r.redirected)
		  return r.text()
		 else
		  location.href = baseURL + 'login'
	})
	.then(html => {
		$(".wishlist-menu").replaceWith(html);
		initListeners();
		if(refreshMainWishlist){
			 $.get(urlWishlist + "/refresh").done(function(fragment) { // get from controller
			     $("#wishlist-body").replaceWith(fragment); // update snippet of page
			     initListeners();
			 });
		}
	})
	.catch(e => console.log(e))
	initListeners();
}

function contactVendor(){
	let username = $('#user-name').val();
	let email = $('#user-email').val();
	let message = $('#user-message-content').val();
	let productId = $('#product-id').val();
	let condition1 = $('#checkbox-legal-1').is(':checked');
	if(condition1 && email !== '' && message !== ''){
		fetch(`${baseURL}products/${productId}/contact`,
			{
				method: 'POST',
				headers: {'Content-Type':'application/json'},
				body: JSON.stringify({username,email,message})
			}
		)
		.then(r => {
			if(r.ok)
				return r.text();
		})
		.then(r => {
			$.magnificPopup.open({
	            items: {
	              src: '<div class="white-popup">'+
	              			'<div class="kt-popup-newsletter">'+
	              				'<div class="popup-title">'+
	              					'<h3>Spring Boot & Thymeleaf</h3>'+
	              					`<p class="notice">Gracias por contactarnos <b>${username}</b>.</p>`+
	              					'<p>Le responderemos lo antes posible.</p>'+
	              				'</div>'+
	              			'</div>'+
	              		'</div>',
	              type: 'inline'
	            }
	          });
		}).catch(e =>{
			console.error(e);
		})
	}
}

function mainContact(){
	let username = $('#user-name').val();
	let email = $('#user-email').val();
	let message = $('#user-message-content').val();
	let productId = $('#product-id').val();
	let condition1 = $('#checkbox-legal-1').is(':checked');
	if(condition1 && email !== '' && message !== ''){
		fetch(`${baseURL}contact`,
			{
				method: 'POST',
				headers: {'Content-Type':'application/json'},
				body: JSON.stringify({username,email,message})
			}
		)
		.then(r => {
			if(r.ok)
				return r.text();
		})
		.then(r => {
			$.magnificPopup.open({
	            items: {
	              src: '<div class="white-popup">'+
	              			'<div class="kt-popup-newsletter">'+
	              				'<div class="popup-title">'+
	              					'<h3>Spring Boot & Thymeleaf</h3>'+
	              					`<p class="notice">Gracias por contactarnos <b>${username}</b>.</p>`+
	              					'<p>Le responderemos lo antes posible.</p>'+
	              				'</div>'+
	              			'</div>'+
	              		'</div>',
	              type: 'inline'
	            }
	          });
			$('#user-name').val('');
		  	$('#user-email').val('');
		    $('#user-message-content').val('');
		}).catch(e =>{
			console.error(e);
		})
	}
}
var files;
function initDragAndDrop(){
	const dropArea = document.querySelector("#drop_zone");
	const dragText = dropArea.querySelector("header");
	
	
	function handleFileSelect(evt) {
		
		let inputFile = document.querySelector("#file-input");
	    evt.stopPropagation();
	    evt.preventDefault();
	    let dt = new DataTransfer();
	    let html = '';
	   
	    let newFiles = evt.dataTransfer.files;
	    let validExtensions = ["image/jpeg", "image/jpg", "image/png"];
	    // files is a FileList of File objects. List some properties.
	    var output = [];
	    for (var i = 0, f; f = newFiles[i]; i++) {
	      /*output.push('<li><strong>', escape(f.name), '</strong> (', f.type || 'n/a', ') - ',
	                  f.size, ' bytes, last modified: ',
	                  f.lastModifiedDate ? f.lastModifiedDate.toLocaleDateString() : 'n/a',
	                  '</li>');*/
	    	let fileType = f.type;
	    	if(validExtensions.includes(fileType)){
	    		html += `<div style="color:black;" class="row">${f.name} <i data-id="${f.name}" data-size="${f.size}" class="fas fa-times remove-file-btn" onclick="removeFile(this)"></i></div>`;
	    		dt.items.add(f);
	    	}else{
	    		$.magnificPopup.open({
	                items: {
	                  src: '<div class="white-popup">'+
	                  			'<div class="kt-popup-newsletter">'+
	                  				'<div class="popup-title">'+
	                  					`<p class="notice">El archivo seleccionado no es una imagen.</p>`+
	                  				'</div>'+
	                  			'</div>'+
	                  		'</div>',
	                  type: 'inline'
	                }
	              });
	    		dropArea.classList.remove("active");
	    	    dragText.textContent = "¡Arrastra y suelta tus imágenes aquí!";
	    	}
	    }
	    //ADD existing files
	    if(files != null){
    	  for (let i = 0; i < files.length; i++) {
    	    const f = files[i]
    	    let duplicate = false;
    	    for (let i = 0; i < dt.files.length; i++) {
    	    	const fd = dt.files[i];
    	    	if(f.name === fd.name && f.size === fd.size ){
    	    		duplicate = true;
    	    		console.log(`Duplicated image ${f.name}`)
    	    	}
    	    }
    	    if(!duplicate){
    	    	dt.items.add(f);
    	    	html += `<div style="color:black;" class="row" onclick="removeFile(this)">${f.name} <i data-id="${f.name}" data-size="${f.size}" class="fas fa-times remove-file-btn" onclick="removeFile(this)"></i></div>`;
    	    }
    	  }
	    }
	    
	     
	    if(html !== ''){
	    	$('#resources-added').html(html);
	    }
	    files = dt.files;
	    inputFile.files = dt.files;
	    
	    dropArea.classList.remove("active");
	    dragText.textContent = "¡Arrastra y suelta tus imágenes aquí!";
	  }

	  function handleDragOver(evt) {
	    evt.stopPropagation();
	    evt.preventDefault();
	    dropArea.classList.add("active");
	    dragText.textContent = "Suelta el archivo para subirlo";
	  }
	  
	  function handleDragLeave(evt){
		  dropArea.classList.remove("active");
		  dragText.textContent = "¡Arrastra y suelta tus imágenes aquí!";
	  }

	  // Setup the dnd listeners.
	  var dropZone = document.getElementById('drop_zone');
	  var dropZoneIntermediate = document.getElementById('drop_zone_intermediate');
	  
	  dropZone.addEventListener('dragover', handleDragOver, false);
	  dropZone.addEventListener('dragleave', handleDragLeave, false);
	  dropZone.addEventListener('drop', handleFileSelect, false);
	  $('#file-input').off().on('change', function(e){
		  handleManualSelection();
	  })
	  dropZoneIntermediate.addEventListener('click', function(e){
		  $('#file-input').click();
	  }, false);
	  
	  
}
function removeFile(obj){
	  let name = obj.dataset.id;
	  let size = obj.dataset.size;
	  let dt = new DataTransfer();
	  let inputFile = document.querySelector("#file-input");
	  let html = '';
	  for (let i = 0; i < inputFile.files.length; i++) {
	    	const f = inputFile.files[i];
	    	if(name !== f.name){
	    		html += `<div style="color:black;" class="row" onclick="removeFile(this)">${f.name} <i data-id="${f.name}" data-size="${f.size}" class="fas fa-times remove-file-btn" onclick="removeFile(this)"></i></div>`;
	    		dt.items.add(f);
	    	}
	  }
	  inputFile.files = dt.files;
	  files = dt.files;
	  
	  $('#resources-added').html(html);
	  
	  
	  
}

function handleManualSelection(){
	let inputFile = document.querySelector("#file-input");
	let dt = new DataTransfer();
	let html = '';
	for (let i = 0; i < inputFile.files.length; i++) {
		const f = inputFile.files[i];
		html += `<div style="color:black;" class="row" onclick="removeFile(this)">${f.name} <i data-id="${f.name}" data-size="${f.size}" class="fas fa-times remove-file-btn" onclick="removeFile(this)"></i></div>`;
	}
	
	files = inputFile.files;
	$('#resources-added').html(html);
	
}