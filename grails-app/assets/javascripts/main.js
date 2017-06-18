$(function() {
    $('.table-responsive').on('show.bs.dropdown', function () {
        $('.table-responsive').css("overflow", "inherit");
    });
    $('.table-responsive').on('hide.bs.dropdown', function () {
        $('.table-responsive').css("overflow", "auto");
    });
    $('.date-input').datetimepicker({
        format: 'YYYY-MM-DD',
        showClear: true,
        showClose: true
    });
    $(".select-input").select2({
        theme: "bootstrap"
    });
    $("[data-toggle='tooltip']").tooltip();
});