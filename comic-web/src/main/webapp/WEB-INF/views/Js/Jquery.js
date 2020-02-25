
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

    var MON = $('.ongoingSeries-MON');
    var TUE = $('.ongoingSeries-TUE');
    var WED = $('.ongoingSeries-WED');
    var THU = $('.ongoingSeries-THU');
    var FRI = $('.ongoingSeries-FRI');
    var SAT = $('.ongoingSeries-SAT');
    var SUN = $('.ongoingSeries-SUN');

    var item_MON = $('.ongoingSeries-item-MON');
    var item_TUE = $('.ongoingSeries-item-TUE');
    var item_WED = $('.ongoingSeries-item-WED');
    var item_THU = $('.ongoingSeries-item-THU');
    var item_FRI = $('.ongoingSeries-item-FRI');
    var item_SAT = $('.ongoingSeries-item-SAT');
    var item_SUN = $('.ongoingSeries-item-SUN');



    var btn_Ongoing = document.getElementById('btn-Ongoing');
    window.onload = function () {
        Say();
    }

    function Say() {
        btn_Ongoing.style.background = '#00d564';
        btn_Ongoing.style.color = 'white';

        MON.css( {
                'background-color':'#00d564',
                'border':'3px',
                'width':'240px',
                'border-color':'#00d564',
                'padding':'15px 0',
                'z-index':'10'
            }
        )

        item_MON.css( {
                'width':'230px',
                'font-weight': 'bold'
            }
        )

        $('.MON-before').css('display','none');
        $('.MON-after').css('display','block');
        $('.TUE-after').css('display','none');
        $('.WED-after').css('display','none');
        $('.THU-after').css('display','none');
        $('.FRI-after').css('display','none');
        $('.SAT-after').css('display','none');
        $('.SUN-after').css('display','none');

        $('.TUE-before').css('display','block');
        $('.WED-before').css('display','block');
        $('.THU-before').css('display','block');
        $('.FRI-before').css('display','block');
        $('.SAT-before').css('display','block');
        $('.SUN-before').css('display','block');
    }


    $('.btn-MON').click(function _MON() {
        MON.css( {
                'background-color':'#00d564',
                'border':'3px',
                'width':'240px',
                'border-color':'#00d564',
                'padding':'15px 0',
                'z-index':'10'
            }
        )
        TUE.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_TUE.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        WED.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_WED.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        THU.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_THU.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        FRI.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_FRI.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        SAT.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_SAT.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        SUN.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_SUN.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        item_MON.css( {
                'width':'230px',
                'font-weight': 'bold'
            }
        )
        $('.MON-before').css('display','none');
        $('.MON-after').css('display','block');
        $('.TUE-after').css('display','none');
        $('.WED-after').css('display','none');
        $('.THU-after').css('display','none');
        $('.FRI-after').css('display','none');
        $('.SAT-after').css('display','none');
        $('.SUN-after').css('display','none');

        $('.TUE-before').css('display','block');
        $('.WED-before').css('display','block');
        $('.THU-before').css('display','block');
        $('.FRI-before').css('display','block');
        $('.SAT-before').css('display','block');
        $('.SUN-before').css('display','block');
    })
    $('.btn-TUE').click(function () {
        TUE.css( {
                'background-color':'#00d564',
                'border':'3px',
                'width':'240px',
                'border-color':'#00d564',
                'padding':'15px 0'
            }
        )

        MON.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })
        item_MON.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        WED.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_WED.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        THU.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_THU.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        FRI.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_FRI.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        SAT.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_SAT.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        SUN.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_SUN.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        item_TUE.css( {
                'width':'230px',
                'font-weight': 'bold'
            }
        )

        $('.TUE-before').css('display','none');
        $('.TUE-after').css('display','block');
        $('.WED-after').css('display','none');
        $('.THU-after').css('display','none');
        $('.FRI-after').css('display','none');
        $('.SAT-after').css('display','none');
        $('.SUN-after').css('display','none');
        $('.MON-after').css('display','none');

        $('.WED-before').css('display','block');
        $('.THU-before').css('display','block');
        $('.FRI-before').css('display','block');
        $('.SAT-before').css('display','block');
        $('.SUN-before').css('display','block');
        $('.MON-before').css('display','block');
    })
    $('.btn-WED').click(function () {
        $('.ongoingSeries-WED').css( {
                'background-color':'#00d564',
                'border':'3px',
                'width':'240px',
                'border-color':'#00d564',
                'padding':'15px 0'
            }
        )
        MON.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })
        item_MON.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        TUE.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_TUE.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )


        THU.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_THU.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        FRI.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_FRI.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        SAT.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_SAT.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        SUN.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_SUN.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )
        $('.ongoingSeries-item-WED').css( {
                'width':'230px',
                'font-weight': 'bold'
            }
        )

        $('.WED-before').css('display','none');
        $('.WED-after').css('display','block');
        $('.THU-after').css('display','none');
        $('.FRI-after').css('display','none');
        $('.SAT-after').css('display','none');
        $('.SUN-after').css('display','none');
        $('.MON-after').css('display','none');
        $('.TUE-after').css('display','none');

        $('.THU-before').css('display','block');
        $('.FRI-before').css('display','block');
        $('.SAT-before').css('display','block');
        $('.SUN-before').css('display','block');
        $('.MON-before').css('display','block');
        $('.TUE-before').css('display','block');
    })
    $('.btn-THU').click(function () {
        $('.ongoingSeries-THU').css( {
                'background-color':'#00d564',
                'border':'3px',
                'width':'240px',
                'border-color':'#00d564',
                'padding':'15px 0'
            }
        )

        MON.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })
        item_MON.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        TUE.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_TUE.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        WED.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_WED.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )


        FRI.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_FRI.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        SAT.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_SAT.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        SUN.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_SUN.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        $('.ongoingSeries-item-THU').css( {
                'width':'230px',
                'font-weight': 'bold'
            }
        )

        $('.THU-before').css('display','none');
        $('.THU-after').css('display','block');
        $('.FRI-after').css('display','none');
        $('.SAT-after').css('display','none');
        $('.SUN-after').css('display','none');
        $('.MON-after').css('display','none');
        $('.TUE-after').css('display','none');
        $('.WED-after').css('display','none');

        $('.FRI-before').css('display','block');
        $('.SAT-before').css('display','block');
        $('.SUN-before').css('display','block');
        $('.MON-before').css('display','block');
        $('.TUE-before').css('display','block');
        $('.WED-before').css('display','block');
    })
    $('.btn-FRI').click(function () {
        $('.ongoingSeries-FRI').css( {
                'background-color':'#00d564',
                'border':'3px',
                'width':'240px',
                'border-color':'#00d564',
                'padding':'15px 0'
            }
        )

        MON.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })
        item_MON.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        TUE.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_TUE.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        WED.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_WED.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        THU.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_THU.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )


        SAT.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_SAT.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        SUN.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_SUN.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        $('.ongoingSeries-item-FRI').css( {
                'width':'230px',
                'font-weight': 'bold'
            }
        )

        $('.FRI-before').css('display','none');
        $('.FRI-after').css('display','block');
        $('.SAT-after').css('display','none');
        $('.SUN-after').css('display','none');
        $('.MON-after').css('display','none');
        $('.TUE-after').css('display','none');
        $('.WED-after').css('display','none');
        $('.THU-after').css('display','none');

        $('.SAT-before').css('display','block');
        $('.SUN-before').css('display','block');
        $('.MON-before').css('display','block');
        $('.TUE-before').css('display','block');
        $('.WED-before').css('display','block');
        $('.THU-before').css('display','block');
    })
    $('.btn-SAT').click(function () {
        $('.ongoingSeries-SAT').css( {
                'background-color':'#00d564',
                'border':'3px',
                'width':'240px',
                'border-color':'#00d564',
                'padding':'15px 0'
            }
        )

        MON.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })
        item_MON.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        TUE.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_TUE.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        WED.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_WED.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        THU.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_THU.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        FRI.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_FRI.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )


        SUN.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_SUN.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        $('.ongoingSeries-item-SAT').css( {
                'width':'230px',
                'font-weight': 'bold'
            }
        )

        $('.SAT-before').css('display','none');
        $('.SAT-after').css('display','block');
        $('.SUN-after').css('display','none');
        $('.MON-after').css('display','none');
        $('.TUE-after').css('display','none');
        $('.WED-after').css('display','none');
        $('.THU-after').css('display','none');
        $('.FRI-after').css('display','none');

        $('.SUN-before').css('display','block');
        $('.MON-before').css('display','block');
        $('.TUE-before').css('display','block');
        $('.WED-before').css('display','block');
        $('.THU-before').css('display','block');
        $('.FRI-before').css('display','block');
    })
    $('.btn-SUN').click(function () {
        $('.ongoingSeries-SUN').css( {
                'background-color':'#00d564',
                'border':'3px',
                'width':'240px',
                'border-color':'#00d564',
                'padding':'15px 0'
            }
        )

        MON.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })
        item_MON.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        TUE.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_TUE.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        WED.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_WED.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        THU.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_THU.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        FRI.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_FRI.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )

        SAT.css( {
            'display': 'flex',
            'flex-direction': 'column',
            'justify-content': 'center',
            'align-items': 'center',
            'width':'153px',
            'padding':'0',
            'background-color':'#f5f5f5',
        })

        item_SAT.css( {
                'width':'153px',
                'font-weight': 'bold',
                'background-color':'#FFFFFF'
            }
        )


        $('.ongoingSeries-item-SUN').css( {
                'width':'230px',
                'font-weight': 'bold'
            }
        )

        $('.SUN-before').css('display','none');
        $('.SUN-after').css('display','block');
        $('.MON-after').css('display','none');
        $('.TUE-after').css('display','none');
        $('.WED-after').css('display','none');
        $('.THU-after').css('display','none');
        $('.FRI-after').css('display','none');
        $('.SAT-after').css('display','none');

        $('.MON-before').css('display','block');
        $('.TUE-before').css('display','block');
        $('.WED-before').css('display','block');
        $('.THU-before').css('display','block');
        $('.FRI-before').css('display','block');
        $('.SAT-before').css('display','block');
    })
})