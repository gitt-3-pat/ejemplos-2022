(function($){
    "use strict"; // Start of use strict
    var stickyHeaderTop =0;
    if( $('.header .main-menu').length > 0){
      stickyHeaderTop = $('.header .main-menu').offset().top;
    }
     /* ---------------------------------------------
     Stick menu
     --------------------------------------------- */
     function boutique_stick_menu(){
      if($('#header .main-menu').length >0){
        var h = $(window).scrollTop();
          var width = $(window).width();
          if(width > 991){
              if((h > stickyHeaderTop) ){
                  $('#header-ontop').addClass('on-sticky');
                  $('#header-ontop').find('.header').addClass('ontop');
              }else{
                  $('#header-ontop').removeClass('on-sticky');
                  $('#header-ontop').find('.header').removeClass('ontop');
              }
          }else{
              $('#header-ontop').find('.header').removeClass('ontop');
              $('#header-ontop').removeClass('on-sticky');
          }
      }
     }
    /* ---------------------------------------------
     Owl carousel
     --------------------------------------------- */
    function init_carousel(){
        $('.owl-carousel').each(function(){
          var config = $(this).data();
          var animateOut = $(this).data('animateout');
          var animateIn = $(this).data('animatein');
          if(typeof animateOut != 'undefined' ){
            config.animateOut = animateOut;
          }
          if(typeof animateIn != 'undefined' ){
            config.animateIn = animateIn;
          }
          var owl = $(this);
          owl.owlCarousel(config);
        });
    }
    /* ---------------------------------------------
     Owl carousel mobile
     --------------------------------------------- */
    function init_carousel_mobile(){
        var window_size = jQuery('body').innerWidth();
        window_size += boutique_get_scrollbar_width();
        if( window_size < 768 ){
          $('.owl-carousel-mobile').each(function(){
            var config = $(this).data();
            var animateOut = $(this).data('animateout');
            var animateIn = $(this).data('animatein');
            if(typeof animateOut != 'undefined' ){
              config.animateOut = animateOut;
            }
            if(typeof animateIn != 'undefined' ){
              config.animateIn = animateIn;
            }
            var owl = $(this);
            owl.owlCarousel(config);
          });
        }
        
    }
    /* ---------------------------------------------
     MENU REPONSIIVE
     --------------------------------------------- */
     function init_menu_reposive(){
          var kt_is_mobile = (Modernizr.touch) ? true : false;
          if(kt_is_mobile === true){
            $(document).on('click', '.boutique-nav .menu-item-has-children > a', function(e){
              var licurrent = $(this).closest('li');
              var liItem = $('.boutique-nav .menu-item-has-children');
              if ( !licurrent.hasClass('show-submenu') ) {
                liItem.removeClass('show-submenu');
                licurrent.parents().each(function (){
                    if($(this).hasClass('menu-item-has-children')){
                     $(this).addClass('show-submenu');   
                    }
                      if($(this).hasClass('main-menu')){
                          return false;
                      }
                })
                licurrent.addClass('show-submenu');
                // Close all child submenu if parent submenu is closed
                if ( !licurrent.hasClass('show-submenu') ) {
                  licurrent.find('li').removeClass('show-submenu');
                  }
                  return false;
                  e.preventDefault();
              }else{
                var href = $this.attr('href');
                  if ( $.trim( href ) == '' || $.trim( href ) == '#' ) {
                      licurrent.toggleClass('show-submenu');
                  }
                  else{
                      window.location = href;
                  } 
              }
              // Close all child submenu if parent submenu is closed
                  if ( !licurrent.hasClass('show-submenu') ) {
                      //licurrent.find('li').removeClass('show-submenu');
                  }
                  e.stopPropagation();
          });
        $(document).on('click', function(e){
              var target = $( e.target );
              if ( !target.closest('.show-submenu').length || !target.closest('.boutique-nav').length ) {
                  $('.show-submenu').removeClass('show-submenu');
              }
          }); 
          // On Desktop
          }else{
              $(document).on('mousemove','.boutique-nav .menu-item-has-children',function(){
                  $(this).addClass('show-submenu');
              })
              $(document).on('mouseout','.boutique-nav .menu-item-has-children',function(){
                  $(this).removeClass('show-submenu');
              })
          }
     }
    /* ---------------------------------------------
     Resize mega menu
     --------------------------------------------- */
     function boutique_resizeMegamenu(){
        var window_size = jQuery('body').innerWidth();
        window_size += boutique_get_scrollbar_width();
        if( window_size > 767 ){
          if( $('#header .main-menu-wapper').length >0){
            var container = $('#header  .main-menu-wapper');
            if( container!= 'undefined'){
              var container_width = 0;
              container_width = container.innerWidth();
              var container_offset = container.offset();
              setTimeout(function(){
                  $('.main-menu .item-megamenu').each(function(index,element){
                      $(element).children('.megamenu').css({'max-width':container_width+'px'});
                      var sub_menu_width = $(element).children('.megamenu').outerWidth();
                      var item_width = $(element).outerWidth();
                      $(element).children('.megamenu').css({'left':'-'+(sub_menu_width/2 - item_width/2)+'px'});
                      var container_left = container_offset.left;
                      var container_right = (container_left + container_width);
                      var item_left = $(element).offset().left;
                      var overflow_left = (sub_menu_width/2 > (item_left - container_left));
                      var overflow_right = ((sub_menu_width/2 + item_left) > container_right);
                      if( overflow_left ){
                        var left = (item_left - container_left);
                        $(element).children('.megamenu').css({'left':-left+'px'});
                      }
                      if( overflow_right && !overflow_left ){
                        var left = (item_left - container_left);
                        left = left - ( container_width - sub_menu_width );
                        $(element).children('.megamenu').css({'left':-left+'px'});
                      }
                  })
              },100);
            }
          }
        }
     }
     function boutique_get_scrollbar_width() {
        var $inner = jQuery('<div style="width: 100%; height:200px;">test</div>'),
            $outer = jQuery('<div style="width:200px;height:150px; position: absolute; top: 0; left: 0; visibility: hidden; overflow:hidden;"></div>').append($inner),
            inner = $inner[0],
            outer = $outer[0];
        jQuery('body').append(outer);
        var width1 = inner.offsetWidth;
        $outer.css('overflow', 'scroll');
        var width2 = outer.clientWidth;
        $outer.remove();
        return (width1 - width2);
    }
    /* ---------------------------------------------
     Height Full
     --------------------------------------------- */
    function js_height_full(){
        (function($){
            var height = $(window).outerHeight();
            $(".full-height").css("height",height);
        })(jQuery);
    }
    function js_width_full(){
        (function($){
            var width = $(window).outerWidth();
            $(".full-width").css("width",width);
        })(jQuery);
    }

    function scrollbar_header_sidebar(){
      //  Scrollbar
      if($('.sidebar-menu').length >0 ){
          $(".sidebar-menu").mCustomScrollbar();
      }
      if($('.header-categoy-menu .inner').length >0 ){
          $(".header-categoy-menu .inner").mCustomScrollbar();
      }
    }
    /* ---------------------------------------------
     POSITION SIDEBAR FOOTER
     --------------------------------------------- */
    function heightheader_categoy_menu(){
        var height2 = $('.header-categoy-menu').outerHeight(),
            height1 = (height2 -140);

        $('.header-categoy-menu .inner').css('height',height1+'px');
    }

    /* ---------------------------------------------
     POSITION SIDEBAR FOOTER
     --------------------------------------------- */
    function positionFootersidebar(){
        var height2 = $('.header.sidebar').outerHeight(),
            height1 = (height2 - 45);
        $('.header.sidebar .sidebar-menu').css('height',height1+'px');
    }

    function clone_main_menu(){
        if( $('.clone-main-menu').length > 0 && $('#box-mobile-menu').length >0){
          $( ".clone-main-menu" ).clone().appendTo( "#box-mobile-menu .box-inner" );
        }
    }
    function clone_header_ontop(){
        if( $('#header').length > 0 && $('#header-ontop').length >0 && $('#header-ontop').hasClass('is-sticky')){
          var content = $( "#header" ).clone();
          content.removeAttr('id');
          content.appendTo( "#header-ontop" );
        }
    }
    /* ---------------------------------------------
     OWL TAB EFFECT
     --------------------------------------------- */
    function tab_owl_fade_effect(){
      // effect first tab
      var sleep = 0;
      $('.tab-owl-fade-effect .nav-tab>li.active a[data-toggle="tab"]').each(function(){
        var tabElement = $(this);
        setTimeout(function() {
            tabElement.trigger('click');
        }, sleep);
        sleep += 10000
      })
      // effect click
      $(document).on('click','.tab-owl-fade-effect a[data-toggle="tab"]',function(){
        var tab_id = $(this).attr('href');
        var tab_animated = $(this).data('animated');
        tab_animated = ( tab_animated == undefined || tab_animated =="" ) ? 'fadeInUp' : tab_animated;
        $(tab_id).find('.owl-item.active').each(function(i){
            var t = $(this);
            var style = $(this).attr("style");
            style     = ( style == undefined ) ? '' : style;
            var delay  = i * 400;
            t.attr("style", style +
                      ";-webkit-animation-delay:" + delay + "ms;"
                    + "-moz-animation-delay:" + delay + "ms;"
                    + "-o-animation-delay:" + delay + "ms;"
                    + "animation-delay:" + delay + "ms;"
            ).addClass(tab_animated+' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
                t.removeClass(tab_animated+' animated');
                t.attr("style", style);
            });
        })
      })
    }

    /* ---------------------------------------------
     PRODUCT TAB EFFECT
     --------------------------------------------- */
    function tab_product_fade_effect(){
      // effect first tab
      var sleep = 0;
      $('.tab-product-fade-effect .nav-tab>li.active a[data-toggle="tab"]').each(function(){
        var tabElement = $(this);
        setTimeout(function() {
            tabElement.trigger('click');
        }, sleep);
        sleep += 10000
      })
      // effect click
      $(document).on('click','.tab-product-fade-effect a[data-toggle="tab"]',function(){
        var tab_id = $(this).attr('href');
        var tab_animated = $(this).data('animated');
        tab_animated = ( tab_animated == undefined || tab_animated =="" ) ? 'fadeInUp' : tab_animated;

        $(tab_id).find('.product-item').each(function(i){
            var t = $(this);
            var style = $(this).attr("style");
            style     = ( style == undefined ) ? '' : style;
            var delay  = i * 400;
            t.attr("style", style +
                      ";-webkit-animation-delay:" + delay + "ms;"
                    + "-moz-animation-delay:" + delay + "ms;"
                    + "-o-animation-delay:" + delay + "ms;"
                    + "animation-delay:" + delay + "ms;"
            ).addClass(tab_animated+' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
                t.removeClass(tab_animated+' animated');
                t.attr("style", style);
            });
        })
      })
    }

    /* ---------------------------------------------
     Init popup
     --------------------------------------------- */
    
    function init_popup(){
        if($(window).width() + boutique_get_scrollbar_width() >= 768){
            if($('body').hasClass('home')){
                //Open directly via API
                $.magnificPopup.open({
                  items: {
                    src: '<div class="white-popup"><div class="kt-popup-newsletter"><div class="popup-title"><h3>BoutiQue</h3><img src="images/arow-popup.png" alt=""><p class="notice">enter your email and get  <span class="primary">25% off</span> YOUR first purchase!</p></div><form class="form-subscribe"><input class="input" placeholder="Your email here" type="text" /><button class="button">NO THANKS!</button><button class="button">Enter</button></form><div class="checkbox"><label><input type="checkbox" value="">Dont show this popup again!</label></div></div></div>',  //can be a HTML string, jQuery object, or CSS selector
                    type: 'inline'
                  }
                });
            }
        }
    }

    function kt_parallax(){
      //parallax
       var window_size =0;
       window_size += boutique_get_scrollbar_width();
        if( window_size > 991 ){
          $('.bg-parallax').each(function(){
                $(this).parallax('50%', 0.3);
          });
        }
    }

    /* ---------------------------------------------
     COUNTDOWN
     --------------------------------------------- */
     function kt_countdown(){
        if($('.kt-countdown').length >0){
          var labels = ['Years', 'Months', 'Weeks', 'Days', 'Hrs', 'Mins', 'Secs'];
          var layout = '<span class="box-count day"><span class="number">{dnn}</span> <span class="text">Days</span></span><span class="dot">:</span><span class="box-count hrs"><span class="number">{hnn}</span> <span class="text">Hrs</span></span><span class="dot">:</span><span class="box-count min"><span class="number">{mnn}</span> <span class="text">Mins</span></span><span class="dot">:</span><span class="box-count secs"><span class="number">{snn}</span> <span class="text">Secs</span></span>';
          $('.kt-countdown').each(function() {
              var austDay = new Date($(this).data('y'),$(this).data('m') - 1,$(this).data('d'),$(this).data('h'),$(this).data('i'),$(this).data('s'));
              $(this).countdown({
                  until: austDay,
                  labels: labels, 
                  layout: layout
              });
          });
        }
     }
    /* ---------------------------------------------
     Scripts initialization
     --------------------------------------------- */
    $(window).load(function() {
      // Resize Megamenu
        boutique_resizeMegamenu();
        init_carousel_mobile();
        js_height_full();
        js_width_full();
        scrollbar_header_sidebar();
        positionFootersidebar();
        clone_main_menu();
        heightheader_categoy_menu();
        boutique_stick_menu();
        clone_header_ontop();
        //init_popup();

        //Group banner
        if( $('.group-banner-masonry').length >0 ){
          $('.group-banner-masonry').isotope(
            {
              itemSelector: '.banner-masonry-item',
              percentPosition: true,
              masonry: {
                // use outer width of grid-sizer for columnWidth
                columnWidth: '.grid-sizer'
              }
            }
          );
        }
    });
    /* ---------------------------------------------
     Scripts resize
     --------------------------------------------- */
    $(window).on("resize", function() {
      // Resize Megamenu
        boutique_resizeMegamenu();
        init_carousel_mobile();
        js_height_full();
        scrollbar_header_sidebar();
        positionFootersidebar();
        js_height_full();
        js_width_full();
        heightheader_categoy_menu();
    });
    /* ---------------------------------------------
     Scripts scroll
     --------------------------------------------- */
    $(window).scroll(function(){
      // Resize Megamenu
      boutique_resizeMegamenu();
      boutique_stick_menu();
      // Show hide scrolltop button
      if( $(window).scrollTop() == 0 ) {
          $('.scroll_top').stop(false,true).fadeOut(600);
      }else{
          $('.scroll_top').stop(false,true).fadeIn(600);
      }
    });
    /* ---------------------------------------------
     Scripts ready
     --------------------------------------------- */
    $(document).ready(function() {
        // OWL CAROUSEL
        init_carousel();
        //MENU REPONSIIVE
        init_menu_reposive();
        //SELECT CHOSEN
        $("select").chosen();
        // Resize Megamenu
        boutique_resizeMegamenu();

        init_carousel_mobile();
        scrollbar_header_sidebar();
        positionFootersidebar();
        heightheader_categoy_menu();
        boutique_stick_menu();
        tab_owl_fade_effect();
        tab_product_fade_effect();
        kt_parallax();
        kt_countdown();
        // CATEGORY FILTER 
        $('.slider-range-price').each(function(){
            var min             = $(this).data('min');
            var max             = $(this).data('max');
            var unit            = $(this).data('unit');
            var value_min       = $(this).data('value-min');
            var value_max       = $(this).data('value-max');
            var label_reasult   = $(this).data('label-reasult');
            var t               = $(this);
            $( this ).slider({
              range: true,
              min: min,
              max: max,
              values: [ value_min, value_max ],
              slide: function( event, ui ) {
                var result = label_reasult +" "+ unit + ui.values[ 0 ] +' - '+ unit +ui.values[ 1 ];
                t.closest('.price_slider_wrapper').find('.amount-range-price').html(result);
              }
            });
        })
        //VIDEO LIGHTBOX
        if ( $('.video-lightbox .link-lightbox').length ){
          $('.video-lightbox .link-lightbox').simpleLightboxVideo();
        }

        //
        $(document).on('click','.box-search.show-icon .icon,.box-search.show-icon .close-box',function(){
          $(this).closest('.box-search').find('.inner').toggle(600);
          $(this).closest('.box-search').toggleClass('open');
        });

       // MENU SIDEBAR

       $(document).on('click','#header .close-header-sidebar',function(){
          $(this).closest('#header').addClass('closed').removeClass('opened');
       })
       $(document).on('click','#header .open-header-sidebar',function(){
          $(this).closest('#header').removeClass('closed').addClass('opened');
       });

        $(document).on('click','.header-categoy-menu .close-header-sidebar',function(){
          $(this).closest('.header-categoy-menu').addClass('closed').removeClass('opened');
       })
       $(document).on('click','.header-categoy-menu .open-header-sidebar',function(){
          $(this).closest('.header-categoy-menu').removeClass('closed').addClass('opened');
       });


       //SLIDE FULL SCREEN
       var slideSection = $(".slide-fullscreen .item-slide");
        slideSection.each(function(){
            if ($(this).data("background")){
              $(this).css("background-image", "url(" + $(this).data("background") + ")");
            }
        });


        // PRODUCT DETAIL IMAGE
        $(document).on('click','.product-detail-image .thumbnails a',function(){
          var url = $(this).data('url');
          $(this).closest('.product-detail-image .thumbnails').find('a').each(function(){
            $(this).removeClass('active');
          })
          $(this).addClass('active');
          $(this).closest('.product-detail-image').find('.main-image').attr('src',url);
          return false;
        })
        

        // TESTANIAL STYLE 1
        $('.testimonials-owl-3').each(function(){
          var owl = $(this).find('.testimonial-owl');
          owl.owlCarousel(
            {
                autoplay:true,
                dots:false,
                loop:true,
                nav:true,
                smartSpeed:1000,
                margin:15,
                navText:['<i class="fa fa-angle-left"></i>','<i class="fa fa-angle-right"></i>'],
                responsive : {
                // breakpoint from 0 up
                0 : {
                    items : 2
                },
                // breakpoint from 480 up
                480 : {
                    items : 2
                },
                // breakpoint from 768 up
                768 : {
                    items : 3
                },
                1000 : {
                    items : 3
                }
            }
            }
          );
          owl.trigger('next.owl.carousel');
          owl.on('changed.owl.carousel', function(event) {
              owl.find('.owl-item.active').removeClass('item-center');
              var caption = owl.find('.owl-item.active').first().next().find('.inner').html();

              var t = owl.closest('.testimonials-owl-3').find('.testimonial-info');
              var animated = t.data('animated');
              t.html(caption).addClass('animated '+animated).one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
                       $(this).removeClass('animated '+animated);
              });
              setTimeout(function(){
                  owl.find('.owl-item.active').first().next().addClass('item-center');
              }, 100);
          })
        });

        // BOX MOBILE MENU
        $(document).on('click','.mobile-navigation',function(){
          $('#box-mobile-menu').addClass('open');
        });
        // Close box menu
        $(document).on('click','#box-mobile-menu .close-menu',function(){
            $('#box-mobile-menu').removeClass('open');
        });
        //  Box mobile menu
        if($('#box-mobile-menu').length >0 ){
            $("#box-mobile-menu").mCustomScrollbar();
        }

        // Box Setting
        $(document).on('click','.box-settings .icon',function(){
          $(this).closest('.box-settings').toggleClass('open');
        })
        // Scroll top 
        $(document).on('click','.scroll_top',function(){
          $('body,html').animate({scrollTop:0},400);
          return false;
        });

        // Select image product
        $(document).on('click','.block-single-product .attributes .item>span',function(){
            var image = $(this).data('image');
            if( typeof image == undefined || image == ""){
              return false;
            }
            $(this).closest('.block-single-product').find('.product-image img').attr('src',image);
            return false;
        })

         //SKILL BAR
        $('.item-processbar').each(function(){
          var $heightSkill = $(this).attr('data-height'),
            $percentSkill = $(this).attr('data-percent'),
            $bgSkill = $(this).attr('data-bgskill'),
            $bgBar = $(this).attr('data-bgBar');

          $(this).find('.processbar-bg').css({
              "height":$heightSkill,
              "background-color":$bgBar

          });
          $(this).find('.processbar-width').css({
              "height":$heightSkill
          });

          $(this).find('.processbar-width').animate({
              'width':$percentSkill+'%'
          },6000);

          if($bgSkill != ''){
             $(this).find('.processbar-width').css({
                'background-color':$bgSkill
              });
            };
        });

        // Fancybox
        if( $('.kt-popup-gallery').length > 0 ){
            $('.kt-popup-gallery').magnificPopup({
                delegate: 'a',
                type: 'image',
                tLoading: 'Loading image #%curr%...',
                mainClass: 'mfp-img-mobile',
                gallery: {
                    enabled: true,
                    navigateByImgClick: true,
                    preload: [0,1] // Will preload 0 - before current, and 1 after the current image
                },
                image: {
                    tError: '<a href="%url%">The image #%curr%</a> could not be loaded.',
                    // titleSrc: function(item) {
                    //   return item.el.attr('title') + '<small>by Marsel Van Oosten</small>';
                    // }
                }
            });
        }
        /* Send conttact*/
        $(document).on('click','#btn-send-contact',function(){
                var email   = $('#email').val(),
                name = $('#name').val(),
                content = $('#content').val();
            var data = {
                email:email,
                content:content,
                name:name
            }

            $(this).html('Loading...');
            var t = $(this);
            $.post('ajax_contact.php',data,function(result){
                if(result.trim()=="done"){
                    $('#email').val('');
                    $('#content').val('');
                    $('#name').val('');
                    $('#message-box-conact').html('<div class="alert alert-info">Your message was sent successfully. Thanks</div>');
                }else{
                    $('#message-box-conact').html(result);
                }
                t.html('SEND MESSAGE');
            })
        })
        // Custom post nav owl
        $('.owl-custom-nav-postion').each(function(){
            var margin = $(this).data('navigation_margin');
            var top = '-'+margin+'px';
            if( typeof (margin) != 'undefined' && margin >=0 ){
                $(this).find('.owl-nav, .owl-nav').css({'top':top});
            }
        })

        // KT BANNER
        $('.kt-banner').each(function(){
          console.log('xxx');
         var minHeight = $(this).data('height'),
         bgimg = $(this).data('background'),
         bgcolor = $(this).data('bgcolor'),
         positiontop = $(this).data('positiontop'),
         positionleft = $(this).data('positionleft'),
         positionright = $(this).data('positionright'),
         positionbottom = $(this).data('positionbottom'),
         position = $(this).data('position');
         var bgcontainer =  $(this).find('.bg-image');
         var content = $(this).find('.banner-content');
         bgcontainer.css({
            'min-height':minHeight+'px',
            'height':minHeight+'px'
         });

       if( typeof(bgimg) != "undefined" && bgimg !=""){
            bgcontainer.css({
             'background-image':'url('+bgimg+')'
            });
         }

         if( typeof(bgcolor) != "undefined" && bgcolor !=""){
            bgcontainer.css({
             'background-color':bgcolor
            });
         }
         
         if( typeof( positiontop ) != "undefined" && positiontop !=""){
            content.css({
             'top':positiontop
            });
         }
         if( typeof( positionleft ) != "undefined" && positionleft !=""){
            content.css({
             'left':positionleft
            });
         }
         if( typeof( positionright ) != "undefined" && positionright !=""){
            content.css({
             'right':positionright
            });
         }
         if( typeof( positionbottom ) != "undefined" && positionbottom !=""){
            content.css({
             'bottom':positionbottom
            });
         }
         if( typeof( position ) != "undefined" && position != "" && position =="center" ){
            content.css({
             '-webkit-transform':'translateY(-50%)',
             '-ms-transform': 'translateY(-50%)',
             '-o-transform': 'translateY(-50%)',
             'transform': 'translateY(-50%)',
             'top':'50%'
            });
         }
      });

      //  Box mobile menu
      if($('.block-category-carousel.scrollbar').length >0 ){
          $(".block-category-carousel.scrollbar .inner").mCustomScrollbar();
      }

      $(document).on('click','.block-category-carousel .block-toggle',function(){
        $(this).closest('.block-category-carousel').toggleClass('open');
        $(this).toggleClass('closed');
      })
    });

})(jQuery); // End of use strict