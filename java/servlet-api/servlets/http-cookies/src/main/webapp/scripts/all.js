var initialized = new Object();
initialized['cookie-name'] = false;

function turnOffTip(id) {
    elem = document.getElementById(id);
    if (!initialized[id]) {
        initialized[id] = true;
        elem.value = "";
        elem['class'] = "";
    }
}