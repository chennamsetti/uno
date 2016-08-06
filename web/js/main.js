var rootURL = "http://localhost:8080/uno/api/games";

var currentGame;

// Retrieve game list when application starts 
findAll();

// Nothing to start or delete in initial application state
$('#btnDelete').hide();
$('#btnStart').hide();

// Register listeners
$('#btnSearch').click(function () {
    search($('#searchKey').val());
    return false;
});

// Trigger search when pressing 'Return' on search key input field
$('#searchKey').keypress(function (e) {
    if (e.which == 13) {
        search($('#searchKey').val());
        e.preventDefault();
        return false;
    }
});

$('#btnAdd').click(function () {
    newGame();
    return false;
});

$('#btnSave').click(function () {
    if ($('#gameId').val() == '')
        createGame();
    return false;
});

$('#btnStart').click(function () {
    if ($('#gameId').val() != '')
        startGame();
    return false;
});

$('#btnDelete').click(function () {
    deleteGame();
    return false;
});

$('#gameList a').live('click', function () {
    findById($(this).data('identity'));
});

function newGame() {
    $('#btnStart').hide();
    $('#btnDelete').hide();
    currentGame = {};
    renderDetails(currentGame); // Display empty form
}

function findAll() {
    //console.log('findAll');
    $.ajax({
        type: 'GET',
        url: rootURL,
        dataType: "json", // data type of response
        success: renderList
    });
}

function findById(id) {
    console.log('findById: ' + id);
    $.ajax({
        type: 'GET',
        url: rootURL + '/' + id,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            $('#btnDelete').show();
            console.log('findById success: ' + data.name);
            currentGame = data;
            renderDetails(currentGame);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('findById error: ' + errorThrown);
        }
    });
}

function createGame() {
    console.log('createGame');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: rootURL,
        dataType: "json",
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
            $('#btnDelete').show();
            $('#gameId').val(data.id);
            findAll();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('createGame error: ' + textStatus);
        }
    });
}

function startGame() {
    console.log('startGame');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: rootURL + '/' + $('#gameId').val(),
        dataType: "json",
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
            findAll();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('startGame error: ' + textStatus);
        }
    });
}

function deleteGame() {
    console.log('deleteGame');
    $.ajax({
        type: 'DELETE',
        url: rootURL + '/' + $('#gameId').val(),
        success: function (data, textStatus, jqXHR) {
            findAll();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('deleteGame error');
        }
    });
}

function renderList(result, status, xhr) {
    // JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
    var list = result == null ? [] : (result instanceof Array ? result : [result]);

    $('#gameList li').remove();
    $.each(list, function (index, game) {
        $('#gameList').append('<li><a href="#" data-identity="' + game.id + '">' + game.name + ' - ' + game.status + '</a></li>');
    });
}

function renderDetails(game) {
    $('#gameId').val(game.id);
    $('#name').val(game.name);

    if (game.status == "STARTED")
        $('#btnStart').hide();
    else if (game.status == "WAITING") {
        $('#btnStart').show();
        $('#btnDelete').hide();
    }
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
    var gameId = $('#gameId').val();
    return JSON.stringify({
        "id": gameId == "" ? null : gameId,
        "name": $('#name').val(),
        "status": $('#status').val()
    });
}
