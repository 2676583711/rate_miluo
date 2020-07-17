function defaultDateTime(defaultDay) {
    let endDate = new Date();
    let startDate = new Date(endDate - (1000 * 60 * 60 * 24 * defaultDay));
    return startDate.getFullYear() + '-'
        + ((startDate.getMonth() + 1) > 9 ? (startDate.getMonth() + 1) : ('0' + (startDate.getMonth() + 1))) + '-'
        + ((startDate.getDate()) > 9 ? (startDate.getDate()) : ('0' + (startDate.getDate())))
        + ' ' + startDate.getHours() + ':00 ~ '
        + endDate.getFullYear() + '-'
        + ((endDate.getMonth() + 1) > 9 ? (endDate.getMonth() + 1) : ('0' + (endDate.getMonth() + 1))) + '-'
        + ((endDate.getDate()) > 9 ? endDate.getDate() : ('0' + (endDate.getDate())))
        + ' ' + endDate.getHours() + ':00'
}
function defaultDateMinuteTime(defaultHour) {
    let endDate = new Date();
    let startDate = new Date(endDate - (1000 * 60 * 60 * defaultHour));
    return startDate.getFullYear() + '-'
        + ((startDate.getMonth() + 1) > 9 ? (startDate.getMonth() + 1) : ('0' + (startDate.getMonth() + 1))) + '-'
        + ((startDate.getDate()) > 9 ? (startDate.getDate()) : ('0' + (startDate.getDate())))
        + ' '
        + ((startDate.getHours()) > 9 ? (startDate.getHours()) : ('0' + (startDate.getHours())))+ ':'
        + ((startDate.getMinutes()) > 9 ? (startDate.getMinutes()) : ('0' + (startDate.getMinutes())))
        +':00 ~ '
        + endDate.getFullYear() + '-'
        + ((endDate.getMonth() + 1) > 9 ? (endDate.getMonth() + 1) : ('0' + (endDate.getMonth() + 1))) + '-'
        + ((endDate.getDate()) > 9 ? endDate.getDate() : ('0' + (endDate.getDate())))
        + ' '
        + ((endDate.getHours()) > 9 ? (endDate.getHours()) : ('0' + (endDate.getHours())))+ ':'
        + ((endDate.getMinutes()) > 9 ? (endDate.getMinutes()) : ('0' + (endDate.getMinutes())))
        + ':00';
}
function defaultMonth() {
    let startDate = new Date();
    return startDate.getFullYear() + '-'
        + ((startDate.getMonth() + 1) > 9 ? (startDate.getMonth() + 1) : ('0' + (startDate.getMonth() + 1)))
}
function defaultOneBeforeMonth() {
    let startDate = new Date();
    let m = startDate.getMonth() === 0 ? 12:(startDate.getMonth());
    return m===12?startDate.getFullYear()-1:startDate.getFullYear() + '-' + (m > 9 ? m : '0' + m);
}
function defaultRangeMonth() {
    let endDate = new Date();
    let startDate = new Date(endDate - 1000 * 60 * 60 * 24 * 3);
    return startDate.getFullYear() + '-'
        + ((startDate.getMonth() + 1) > 9 ? (startDate.getMonth() + 1) : ('0' + (startDate.getMonth() + 1)))
        + ' ~ '
        + endDate.getFullYear() + '-'
        + ((endDate.getMonth() + 1) > 9 ? (endDate.getMonth() + 1) : ('0' + (endDate.getMonth() + 1)))
}
function defaultDate(beforeDay) {
    let endDate = new Date(new Date() - 1000 * 60 * 60 * 24 * (beforeDay?beforeDay:1));
    let startDate = new Date(endDate - 1000 * 60 * 60 * 24 * 14);
    return startDate.getFullYear() + '-'
        + ((startDate.getMonth() + 1) > 9 ? (startDate.getMonth() + 1) : ('0' + (startDate.getMonth() + 1))) + '-'
        + ((startDate.getDate()) > 9 ? startDate.getDate() : ('0' + (startDate.getDate())))
        + ' ~ '
        + endDate.getFullYear() + '-'
        + ((endDate.getMonth() + 1) > 9 ? (endDate.getMonth() + 1) : ('0' + (endDate.getMonth() + 1))) + '-'
        + ((endDate.getDate()) > 9 ? endDate.getDate() : ('0' + (endDate.getDate())))
}