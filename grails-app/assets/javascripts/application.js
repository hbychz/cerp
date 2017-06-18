// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= encoding UTF-8
//= require jquery-2.2.0.min
//= require bootstrap
//= require vue
//= require jquery-ui
//= require fastclick.min
//= require jquery.slimscroll.min
//= require moment
//= require moment-zh-cn
//= require bootstrap-datetimepicker
//= require select2.full
//= require select2-zh-cn
//= require sweetalert.min
//= require velocity.min
//= require velocity.ui.min
//= require AdminLTE
//= require main
//= require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {
    (function($) {
        $('#spinner').ajaxStart(function() {
            $(this).fadeIn();
        }).ajaxStop(function() {
            $(this).fadeOut();
        });
    })(jQuery);
}
