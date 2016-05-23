(function (window) {
    window.watchResize = function (callback) {
        var resizing;
        callback.size = 0;
        function done() {
            var curr_size = window.innerWidth;
            clearTimeout(resizing);
            resizing = null;
            // only run on a true resize
            if (callback.size != curr_size) {
                callback();
                callback.size = curr_size;
            }
        }

        window.addEventListener('resize', function () {
            if (resizing) {
                clearTimeout(resizing);
                resizing = null;
            }
            resizing = setTimeout(done, 50);
        });
        // init
        callback();
    };
}(window));

// Get the active Media Query as defined in the CSS
// Use the following format:
// #getActiveMQ-watcher { font-family: "default"; }
// @media only screen and (min-width:20em){ #getActiveMQ-watcher { font-family: "small"; } }
// etc.
window.getActiveMQ = function () {
    // Build the watcher
    var $watcher = document.createElement('div'),
    // alias getComputedStyle
        computed = window.getComputedStyle,
    // Regexp for removing quotes
        re = /['"]/g;

    // set upt the watcher and add it to the DOM
    $watcher.setAttribute('id', 'getActiveMQ-watcher');
    $watcher.style.display = 'none';
    document.body.appendChild($watcher);

    // We’ll redefine this method the first time it’s run
    // For old IE
    if ('currentStyle' in $watcher) {
        window.getActiveMQ = function () {
            return $watcher.currentStyle['fontFamily'].replace(re, '');
        };
    }
    // For modern browsers
    else if (computed) {
        window.getActiveMQ = function () {
            return computed($watcher, null).getPropertyValue('font-family').replace(re, '');
        };
    }
    // For everything else
    else {
        window.getActiveMQ = function () {
            return 'unknown';
        };
    }
    var mq = window.getActiveMQ();

    return mq;
};

if (window.watchResize) {
    window.watchResize(function () {

            var activeMQ = getActiveMQ();
            console.log('activeMQ: ' + activeMQ);

            var width_em = window.innerWidth / parseFloat(window.getComputedStyle(document.body).getPropertyValue("font-size"));
            document.getElementById('debug').innerHTML = activeMQ + ' / ' + width_em.toFixed(1) + 'em';


            var previewElements = document.querySelectorAll('[data-preview-img]');
            for (var i = 0; i < previewElements.length; i++) {

                var s = previewElements[i].getAttribute('data-preview-img');
                //console.log(s);
                var pattern = new RegExp('mq([0-9])=([hw]+[0-9]+)', 'g');
                var updated=false;

                var result;
                var count=0;

                while((result = pattern.exec(s))) {
                    count++;
                    var mq = result[1];
                    if (activeMQ==mq) {
                        s = s.replace(/\(mq[0-9]=.*\)/g,result[2]);
                        previewElements[i].innerHTML = '<br/> <img src="' + s + '"/>';
                        updated=true;
                    }
                }
                if (!updated) {
                    previewElements[i].innerHTML='';
                }

            }

        /*
            if (size > "1") {
                var buttons = document.querySelectorAll('[data-preview-img]');
                for (var i = 0; i < buttons.length; i++) {
                    console.log()
                    buttons[i].innerHTML = '<br/> <img src="' + buttons[i].getAttribute('data-preview-img') + '"/>';
                }
            } else {
                var buttons = document.querySelectorAll('[data-preview-img]');
                for (var i = 0; i < buttons.length; i++) {
                    buttons[i].innerHTML = '';
                }
            }
*/
            //alert(size);
        }
    )
    ;
}