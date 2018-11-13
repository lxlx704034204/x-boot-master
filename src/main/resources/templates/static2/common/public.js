
function checkOption(optionsArray, optionIndex) {
    $.each(optionsArray, function (index, item) {
        item.checked = false;
    });
    optionsArray[optionIndex].checked = true;
}