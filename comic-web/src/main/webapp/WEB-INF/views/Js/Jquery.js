
$(document).ready(function () {
    // $('#onGoing').click(function () {
    //     $('#onGoing').addClass('header-ongoing-item-a-after').removeClass('header-ongoing-item-a-before');
    //     $('#comPlete').removeClass('header-ongoing-item-a-after').addClass('header-ongoing-item-a-before');
    // });
    // $('#comPlete').click(function () {
    //     $('#comPlete').addClass('header-ongoing-item-a-after').removeClass('header-ongoing-item-a-before');
    //     $('#onGoing').removeClass('header-ongoing-item-a-after').addClass('header-ongoing-item-a-before');
    // })
    $('.main-carousel').flickity({
        // options
        cellAlign: 'left',
        contain: true,
        autoPlay: 1500
    });

})