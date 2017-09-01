"use strict";
(function(){
    window.onload = function(){
        var speed;
        var reader;
        var passage;
        var word;
        var running = false;
        var $ = function(id) { return document.getElementById(id);};
        $("stop").disabled = true;
        $("start").onclick = function() {
            $("start").disabled = true;
            $("textarea").disabled = true;
            $("stop").disabled = false;
            if(!running){
                init();
                running = true;
                reader = setInterval(read, speed);
            }
        };
        $("stop").onclick = stop;
        function stop() {
            $("start").disabled = false;
            $("stop").disabled = true;
            $("textarea").disabled = false;
            clearInterval(reader);
            passage = null;
            word = null;
            running = false;
        }
        $("select").onchange = function(){
            if(running){
                clearInterval(reader);
                speed = getSpeed();
                reader = setInterval(read, speed);
            }
        };
        $("sizeRadios").onclick = function(){
            $("display").style.fontSize = getSize();
        };
        function read(){
            if(word >= passage.length){
                running = false;
                stop();
                clearInterval(reader);
            }
            else{
                $("display").innerHTML = passage[word];
                if(!(word > passage.length)){
                    word++;
                }
            }
        }
        function init(){
            speed = getSpeed();
            passage = $("textarea").value.split(/[ \t\n]+/);
            word = 0;
        }
        function getSpeed(){
            var e = $("select");
            return e.options[e.selectedIndex].value;
        }
        function getSize(){
            var options = document.getElementsByName("size");
            for(var i = 0; i < options.length; i++) {
                if (options[i].checked) {
                    return options[i].value;
                }
            }
        }
    };
})();