$(document).ready(function(){
    $(".block_book_prev_category a").hover(function() {
        $(this).next("em").animate({opacity: "show", top: "-75"}, "slow");
    }, function() {
        $(this).next("em").animate({opacity: "hide", top: "-85"}, "fast");
    });
});