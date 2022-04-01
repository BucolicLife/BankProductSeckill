function send(method, url, data, f) {
    $.ajax({
        type: method,
        url: url,
        data: data,
        dataType: 'JSON',
        success: f,
    });
}