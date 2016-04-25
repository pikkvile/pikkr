$(function() {
    $('#add-image').click(function() {
        var $this = $(this);
        var lastPosition = $this.data('lastPosition');
        lastPosition++;
        $this.data('lastPosition', lastPosition);
        var $img = $('<div class="form-group"><input type="file" class="form-control" name="file' + lastPosition + '" /></div>');
        $img.insertBefore($this);
    });
});