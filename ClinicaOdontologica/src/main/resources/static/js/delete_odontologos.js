function deleteBy(id) {
    const url = '/odontologos/' + id;
    const settings = {
        method: 'DELETE'
    }
    fetch(url, settings)
        .then(response => response.json())

        let rowId = '#tr_' + id;
        document.querySelector(rowId).remove();

}
