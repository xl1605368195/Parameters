const showSuccessNotify = function showSuccessNotify(title, message) {
    PNotify.prototype.options.styling = "bootstrap3";
    new PNotify({
        title: title,
        text: message,
        hide: true,
        delay: 4000,
        sticker: false,
        addclass: 'pnotify-light',
        type: 'success'
    });
};

const showErrorNotify = function (title, message) {
    PNotify.prototype.options.styling = "bootstrap3";
    new PNotify({title: title, text: message, delay: 3000, icon: "fa fa-exclamation-triangle", type: 'error'});
};

const showNoticeNotify = function (title, message) {
    PNotify.prototype.options.styling = "bootstrap3";
    new PNotify({title: title, text: message, delay: 3000, icon: "fa fa-exclamation-triangle", type: 'notice'});
};