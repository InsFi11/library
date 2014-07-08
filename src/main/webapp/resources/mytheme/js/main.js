/**
 * Created by JetBrains PhpStorm.
 * User: Андрей
 * Date: 09.10.13
 * Time: 12:16
 * To change this template use File | Settings | File Templates.
 */

$(document).ready(function(){

    $('#button').click(function(){
        alertify.alert('works!');
    });

    $('.bxslider').bxSlider({
        mode: 'fade',
        captions: true,
        pager: false,
        auto: true,
        autoHover: true,
        pause: 8000
    });
});