/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package browseractioncapture;
//import org.openqa.selenium.By;

import index.html.OldFrame;
import java.util.Arrays;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

//import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author nvlakshmidurgab
 */
public class BrowserActionCapture {
    
    public void openBrowserWithUrl(){
        System.setProperty("webdriver.chrome.driver", "/Users/nvlakshmidurgab/Downloads/drivers/chromedriver");//chromedriver
        //ChromeOptions options = new ChromeOptions ();
///        options.addExtensions (new File("/Users/nvlakshmidurgab/Downloads/Katalon-Recorder-(Selenium-IDE-for-Chrome)_v3.4.10.crx"));
       // DesiredCapabilities capabilities = new DesiredCapabilities ();
       // capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");// url variable
        driver.executeScript("(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require==\"function\"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error(\"Cannot find module '\"+o+\"'\")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require==\"function\"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){\n" +
"/*jslint nomen: true*/\n" +
"/*global $,define,require,module */\n" +
"\n" +
"var recorder = require('./recorder');\n" +
"var eventsToRecord = require('./events-to-record');\n" +
"var codeGenerator = require('./code-generator-css');\n" +
"\n" +
"recorder.init({\n" +
"    generateCode: codeGenerator.generateCode,\n" +
"    eventsToRecord: eventsToRecord\n" +
"});\n" +
"window.recorder = recorder;\n" +
"module.exports = recorder;\n" +
"},{\"./code-generator-css\":2,\"./events-to-record\":8,\"./recorder\":9}],2:[function(require,module,exports){\n" +
"/*jslint nomen: true*/\n" +
"/*global $,define,require,module */\n" +
"\n" +
"var eventCodingMap = require('./event-coding-map'),\n" +
"    cssSelectorFactory = require('./css-selector-factory'),\n" +
"    eventCoordinators = require('./event-coordinates');\n" +
"\n" +
"// function generateCode(evt) {\n" +
"//     var cssSelector = cssSelectorFactory.getSelector(evt.target),\n" +
"//         code = eventCodingMap.getEventCode(evt),\n" +
"//         coordinates = eventCoordinators.getClientCoordinates(evt);\n" +
"\n" +
"//     if (code) {\n" +
"//         return code + '(\\'' + cssSelector + '\\', ' + JSON.stringify(coordinates) + ')';\n" +
"//     }\n" +
"\n" +
"//     return evt.type + ' \\'' + cssSelector + '\\' ' + JSON.stringify(coordinates);\n" +
"// }\n" +
"function generateCode(evt) {\n" +
"    var cssSelector = cssSelectorFactory.getSelector(evt.target),\n" +
"        code = eventCodingMap.getEventCode(evt),\n" +
"        coordinates = eventCoordinators.getClientCoordinates(evt);\n" +
"\n" +
"    if (code) {\n" +
"        return code + ',' + cssSelector + ',' + JSON.stringify(coordinates);\n" +
"    }\n" +
"\n" +
"    return evt.type + ',' + cssSelector + ',' + JSON.stringify(coordinates);\n" +
"}\n" +
"\n" +
"module.exports = {\n" +
"    generateCode: generateCode\n" +
"};\n" +
"},{\"./css-selector-factory\":3,\"./event-coding-map\":6,\"./event-coordinates\":7}],3:[function(require,module,exports){\n" +
"/*jslint nomen: true*/\n" +
"/*global $,define,require,module */\n" +
"\n" +
"var dom = require('./dom');\n" +
"\n" +
"function getIdOrCls(el) {\n" +
"    if (el.id) {\n" +
"        return '#' + el.id;\n" +
"    } else if (el.classList && el.classList.length > 0) {\n" +
"        return '.' + el.className.split(' ').join('.');\n" +
"    }\n" +
"    return '';\n" +
"}\n" +
"\n" +
"function getCssSelector(el) {\n" +
"    var selectorList = ['', ''],\n" +
"        selector,\n" +
"        parentEl;\n" +
"\n" +
"    selectorList[1] = getIdOrCls(el);\n" +
"\n" +
"    if (el.id) {\n" +
"        return selectorList[1];\n" +
"    }\n" +
"\n" +
"    if (selectorList[1].length === 0) {\n" +
"        selector = el.nodeName;\n" +
"\n" +
"        if (selector === 'A') {\n" +
"            selector += ':contains(' + el.textContent + ')'\n" +
"        }\n" +
"        selectorList[1] = selector;\n" +
"    }\n" +
"\n" +
"    parentEl = dom.up(el, function (element) {\n" +
"        return getIdOrCls(element).length > 0;\n" +
"    });\n" +
"\n" +
"    selectorList[0] = getIdOrCls(parentEl);\n" +
"\n" +
"    return selectorList.join(' ').trim();\n" +
"}\n" +
"\n" +
"module.exports = {\n" +
"    getSelector: getCssSelector\n" +
"};\n" +
"},{\"./dom\":5}],4:[function(require,module,exports){\n" +
"/*jslint nomen: true*/\n" +
"/*global $,define,require,module */\n" +
"\n" +
"function isEnterText(evt) {\n" +
"    var element = evt.target;\n" +
"    return (element.type === 'text' || element.type === 'textarea') && evt.type === 'keyup';\n" +
"}\n" +
"\n" +
"function getCustomEventType(evt) {\n" +
"    if (isEnterText(evt)) {\n" +
"        return 'enterText';\n" +
"    }\n" +
"}\n" +
"\n" +
"module.exports = {\n" +
"    getType: getCustomEventType\n" +
"};\n" +
"},{}],5:[function(require,module,exports){\n" +
"/*jslint nomen: true*/\n" +
"/*global $,define,require,module */\n" +
"\n" +
"function up(el, stopCondition) {\n" +
"    var target = el;\n" +
"\n" +
"    while (target.parentNode) {\n" +
"        target = target.parentNode;\n" +
"        if (stopCondition(target)) {\n" +
"            break;\n" +
"        }\n" +
"    }\n" +
"    return target;\n" +
"}\n" +
"\n" +
"module.exports = {\n" +
"    up: up\n" +
"};\n" +
"},{}],6:[function(require,module,exports){\n" +
"/*jslint nomen: true*/\n" +
"/*global $,define,require,module */\n" +
"\n" +
"var customEvent = require('./custom-event'),\n" +
"    codingMap = {\n" +
"        click: '.waitAndClick',\n" +
"        enterText: '.typeValue' // this is a non-existing event to represent type in values to a textbox or textarea\n" +
"    };\n" +
"\n" +
"function getEventCode(evt) {\n" +
"    var code = codingMap[evt.type];\n" +
"\n" +
"    if (code) {\n" +
"        return code;\n" +
"    }\n" +
"\n" +
"    // handle non-existing events\n" +
"    return codingMap[customEvent.getType(evt)];\n" +
"}\n" +
"\n" +
"module.exports = {\n" +
"    getEventCode: getEventCode\n" +
"};\n" +
"},{\"./custom-event\":4}],7:[function(require,module,exports){\n" +
"/*jslint nomen: true*/\n" +
"/*global $,define,require,module */\n" +
"\n" +
"var eventsWithCoordinates = {\n" +
"    mouseup: true,\n" +
"    mousedown: true,\n" +
"    mousemove: true,\n" +
"    mouseover: true\n" +
"};\n" +
"\n" +
"function getClientCoordinates(evt) {\n" +
"    if (!eventsWithCoordinates[evt.type]) {\n" +
"        return '';\n" +
"    }\n" +
"\n" +
"    return {\n" +
"        x: evt.clientX,\n" +
"        y: evt.clientY\n" +
"    };\n" +
"}\n" +
"\n" +
"module.exports = {\n" +
"    getClientCoordinates: getClientCoordinates\n" +
"};\n" +
"},{}],8:[function(require,module,exports){\n" +
"/*jslint nomen: true*/\n" +
"/*global $,define,require,module */\n" +
"\n" +
"module.exports = [\n" +
"    'click',\n" +
"//    'focus',\n" +
"//    'blur',\n" +
"    'dblclick',\n" +
"    'change',\n" +
"    'keyup',\n" +
"//    'keydown',\n" +
"//    'keypress',\n" +
"    'mousedown',\n" +
"//    'mousemove',\n" +
"//    'mouseout',\n" +
"//    'mouseover',\n" +
"//    'mouseup',\n" +
"    'resize',\n" +
"//    'scroll',\n" +
"    'select',\n" +
"    'submit',\n" +
"    'load',\n" +
"    'unload'\n" +
"];\n" +
"\n" +
"//var events = [\n" +
"//    abort,\n" +
"//    afterprint,\n" +
"//    beforeprint,\n" +
"//    beforeunload,\n" +
"//    blur,\n" +
"//    canplay,\n" +
"//    canplaythrough,\n" +
"//    change,\n" +
"//    click,\n" +
"//    contextmenu,\n" +
"//    copy,\n" +
"//    cuechange,\n" +
"//    cut,\n" +
"//    dblclick,\n" +
"//    DOMContentLoaded,\n" +
"//    drag,\n" +
"//    dragend,\n" +
"//    dragenter,\n" +
"//    dragleave,\n" +
"//    dragover,\n" +
"//    dragstart,\n" +
"//    drop,\n" +
"//    durationchange,\n" +
"//    emptied,\n" +
"//    ended,\n" +
"//    error,\n" +
"//    focus,\n" +
"//    focusin,\n" +
"//    focusout,\n" +
"//    formchange,\n" +
"//    forminput,\n" +
"//    hashchange,\n" +
"//    input,\n" +
"//    invalid,\n" +
"//    keydown,\n" +
"//    keypress,\n" +
"//    keyup,\n" +
"//    load,\n" +
"//    loadeddata,\n" +
"//    loadedmetadata,\n" +
"//    loadstart,\n" +
"//    message,\n" +
"//    mousedown,\n" +
"//    mouseenter,\n" +
"//    mouseleave,\n" +
"//    mousemove,\n" +
"//    mouseout,\n" +
"//    mouseover,\n" +
"//    mouseup,\n" +
"//    mousewheel,\n" +
"//    offline,\n" +
"//    online,\n" +
"//    pagehide,\n" +
"//    pageshow,\n" +
"//    paste,\n" +
"//    pause,\n" +
"//    play,\n" +
"//    playing,\n" +
"//    popstate,\n" +
"//    progress,\n" +
"//    ratechange,\n" +
"//    readystatechange,\n" +
"//    redo,\n" +
"//    reset,\n" +
"//    resize,\n" +
"//    scroll,\n" +
"//    seeked,\n" +
"//    seeking,\n" +
"//    select,\n" +
"//    show,\n" +
"//    stalled,\n" +
"//    storage,\n" +
"//    submit,\n" +
"//    suspend,\n" +
"//    timeupdate,\n" +
"//    undo,\n" +
"//    unload,\n" +
"//    volumechange,\n" +
"//    waiting\n" +
"//];\n" +
"},{}],9:[function(require,module,exports){\n" +
"/*jslint nomen: true*/\n" +
"/*global $,define,require,module */\n" +
"\n" +
"// var recordedCode = '',\n" +
"var recordedCode = [],\n" +
"    generateCode,\n" +
"    generateObject,\n" +
"    eventsToRecord,\n" +
"    windowToListen;\n" +
"\n" +
"function init(config) {\n" +
"    generateCode = config.generateCode;\n" +
"    generateObject = config.generateObject;\n" +
"    eventsToRecord = config.eventsToRecord;\n" +
"}\n" +
"\n" +
"function setWindowToListen(windowElement) {\n" +
"    windowToListen = windowElement;\n" +
"}\n" +
"\n" +
"// Each frame is a window\n" +
"function getAllFrames(windowElement, allFrames) {\n" +
"    try{\n" +
"        allFrames.push(windowElement.frames);\n" +
"        for (var i = 0; i < windowElement.frames.length; i++) {\n" +
"            getAllFrames(windowElement.frames[i], allFrames);\n" +
"        }\n" +
"        return allFrames;\n" +
"    }catch(err){\n" +
"        console.error('There is error in getting frame')\n" +
"    }\n" +
"   \n" +
"}\n" +
"\n" +
"function getElementsToListen(windowElement) {\n" +
"    return getAllFrames(windowElement, []);\n" +
"}\n" +
"\n" +
"function bind(el, eventType, handler) {\n" +
"    try{\n" +
"        if (el.addEventListener) { // DOM Level 2 browsers\n" +
"            el.addEventListener(eventType, handler, false);\n" +
"        } else if (el.attachEvent) { // IE <= 8\n" +
"            el.attachEvent('on' + eventType, handler);\n" +
"        } else { // ancient browsers\n" +
"            el['on' + eventType] = handler;\n" +
"        }\n" +
"    }catch(err){\n" +
"        console.error('error in bind',err);\n" +
"    }\n" +
"    \n" +
"}\n" +
"\n" +
"function unbind(el, eventType, handler) {\n" +
"    if (el.removeEventListener) {\n" +
"        el.removeEventListener(eventType, handler, false);\n" +
"    } else if (el.detachEvent) {\n" +
"        el.detachEvent(\"on\" + eventType, handler);\n" +
"    } else {\n" +
"        el[\"on\" + eventType] = null;\n" +
"    }\n" +
"}\n" +
"\n" +
"function manageSingleElementEvents(element, action, events, handler) {\n" +
"    var eventIndex = 0,\n" +
"        eventCount = events.length;\n" +
"\n" +
"    for (; eventIndex < eventCount; eventIndex++) {\n" +
"        action(element, events[eventIndex], handler);\n" +
"    }\n" +
"}\n" +
"\n" +
"function manageEvents(elements, action, events, handler) {\n" +
"    var elementIndex = 0,\n" +
"        elementCount = elements.length;\n" +
"\n" +
"    for (; elementIndex < elementCount; elementIndex++) {\n" +
"        // Have to attach events with some delay between iframes. Otherwise, iframe events are not captured\n" +
"        setTimeout(manageSingleElementEvents, 50, elements[elementIndex], action, events, handler);\n" +
"    }\n" +
"}\n" +
"\n" +
"function recordEvent(e) {\n" +
"    var code;\n" +
"\n" +
"    if (generateObject) {\n" +
"        console.recorderLog(JSON.stringify(generateObject(e), true, 2));\n" +
"    }\n" +
"    if (generateCode) {\n" +
"        code = [generateCode(e)];\n" +
"\n" +
"        // recordedCode = recordedCode + code + '\\n';\n" +
"        recordedCode.push(code);\n" +
"        console.recorderLog(code);\n" +
"    }\n" +
"}\n" +
"\n" +
"function record() {\n" +
"    var elementsToListen = getElementsToListen(windowToListen || window);\n" +
"    console.recorderLog = console.log; // hijack the console.log so that only recorded code will be shown\n" +
"    console.log = function () {};\n" +
"    manageEvents(elementsToListen, bind, eventsToRecord, recordEvent);\n" +
"}\n" +
"\n" +
"function stop() {\n" +
"    var elementsToListen = getElementsToListen(windowToListen || window);\n" +
"    if (console.recorderLog) {\n" +
"        console.log = console.recorderLog;\n" +
"    }\n" +
"    manageEvents(elementsToListen, unbind, eventsToRecord, recordEvent);\n" +
"}\n" +
"\n" +
"// function getRecordedCode() {\n" +
"//     return recordedCode;\n" +
"// }\n" +
"/* generate Excel */\n" +
"function getRecordedCode() {\n" +
"    url = toExcelUrl(recordedCode);\n" +
"    // Create link.\n" +
"    a = document.createElement( \"a\" );\n" +
"    // Set link on DOM.\n" +
"    document.body.appendChild( a );\n" +
"    // Set link's visibility.\n" +
"    a.style = \"display: none\";\n" +
"    // Set href on link.\n" +
"    a.href = url;\n" +
"    // Set file name on link.\n" +
"    a.download = \"report.xls\";//file name can be passed\n" +
"    // Trigger click of link.\n" +
"    a.click();\n" +
"    // Clear.\n" +
"    window.URL.revokeObjectURL( url );\n" +
"    // return recordedCode;\n" +
"}\n" +
"\n" +
"function clearRecordedCode() {\n" +
"    return recordedCode = '';\n" +
"}\n" +
"function toExcelUrl(data) {\n" +
"     console.log(data);\n" +
"    var tsv = data.map(function(row) { console.log(row); return  Array.prototype.join.call(row,'\\t'); }).join('\\n');\n" +
"    var blob = new Blob([tsv], {type:'application/vnd.ms-excel'});\n" +
"    return URL.createObjectURL(blob);\n" +
"}\n" +
"module.exports = {\n" +
"    init: init,\n" +
"    setWindowToListen: setWindowToListen,\n" +
"    record: record,\n" +
"    stop: stop,\n" +
"    getRecordedCode: getRecordedCode,\n" +
"    clearRecordedCode: clearRecordedCode\n" +
"};\n" +
"\n" +
"},{}]},{},[1])");
       
        // after record action is clicked execute this
//        driver.executeScript("recorder.record()");
       //get the record data with passing the file name
//       driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//       driver.executeScript("recorder.stop()");
//        Object actions = (Object)driver.executeScript("return recorder.getRecordedCode()");
        // stop cmd for recorder
//         driver.executeScript("recorder.stop()");
//        System.out.println("the actions are"+actions.toString());
//        driver.get("chrome-extension://ljdobmomdgdljniojadhoplhkpialdid/panel/index.html");
//        driver.findElement(By.id("record")).click();
        
    }
    
//    public void captureBrowserAction(){
//        
////        driver = new ChromeDriver();
////        ChromeOptions options = new ChromeOptions ();
////        options.addExtensions (new File("/Users/nvlakshmidurgab/Downloads/Selenium-IDEv_1.0.3.crx"));
////        options.addArguments("headless");
////        DesiredCapabilities capabilities = new DesiredCapabilities ();
////        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        //WebDriver driver = new ChromeDriver(capabilities);
//        driver = new HtmlUnitDriver();
//        driver.get("https://www.w3schools.com/");
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//  
//        //Opening URL In HtmlUnitDriver.
//        driver.get("http://www.google.com");
//        System.out.println("Page title before search : "+ driver.getTitle());
//         //Search with Hello World on google.
//        WebElement Searchbox = driver.findElement(By.xpath("//input[@name='q']"));
//        Searchbox.sendKeys("Hello World");
//        Searchbox.submit();
//        System.out.println("Page title after search : "+driver.getTitle());
//  
////        driver.get("chrome-extension://mooikfkahbdckldjjndioackbalphokd/assets/index.html");
////        WebElement button  = driver.findElement(By.className("sc-bdVaJa juJWwv"));
////        button.click();
////       chrome-extension://ljdobmomdgdljniojadhoplhkpialdid/panel/index.html
//    }
    public void invokeGeckoFirefox(boolean isMarionette) {
//        LOGGER.trace(LoggerUtil.ENTERING);
 System.setProperty("webdriver.gecko.driver", "/Users/nvlakshmidurgab/Downloads/drivers/geckodriver");
        FirefoxDriver dr = null;
        FirefoxOptions firefoxOptions = new FirefoxOptions();
//        firefoxOptions.setCapability("firefox.switches", Arrays.asList(IGNORE_CERTIFICATE_ERRORS));

        FirefoxProfile profile = new FirefoxProfile();
        firefoxOptions.setCapability(FirefoxDriver.PROFILE, profile);
        firefoxOptions.setCapability("marionette", isMarionette);
        try {
            dr = new FirefoxDriver(firefoxOptions);
            dr.get("http://google.com");
            dr.executeScript("alert('hi')");
            dr.executeScript("(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require==\"function\"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error(\"Cannot find module '\"+o+\"'\")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require==\"function\"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){\n" +
"/*jslint nomen: true*/\n" +
"/*global $,define,require,module */\n" +
"\n" +
"var recorder = require('./recorder');\n" +
"var eventsToRecord = require('./events-to-record');\n" +
"var codeGenerator = require('./code-generator-css');\n" +
"\n" +
"recorder.init({\n" +
"    generateCode: codeGenerator.generateCode,\n" +
"    eventsToRecord: eventsToRecord\n" +
"});\n" +
"window.recorder = recorder;\n" +
"module.exports = recorder;\n" +
"},{\"./code-generator-css\":2,\"./events-to-record\":8,\"./recorder\":9}],2:[function(require,module,exports){\n" +
"/*jslint nomen: true*/\n" +
"/*global $,define,require,module */\n" +
"\n" +
"var eventCodingMap = require('./event-coding-map'),\n" +
"    cssSelectorFactory = require('./css-selector-factory'),\n" +
"    eventCoordinators = require('./event-coordinates');\n" +
"\n" +
"// function generateCode(evt) {\n" +
"//     var cssSelector = cssSelectorFactory.getSelector(evt.target),\n" +
"//         code = eventCodingMap.getEventCode(evt),\n" +
"//         coordinates = eventCoordinators.getClientCoordinates(evt);\n" +
"\n" +
"//     if (code) {\n" +
"//         return code + '(\\'' + cssSelector + '\\', ' + JSON.stringify(coordinates) + ')';\n" +
"//     }\n" +
"\n" +
"//     return evt.type + ' \\'' + cssSelector + '\\' ' + JSON.stringify(coordinates);\n" +
"// }\n" +
"function generateCode(evt) {\n" +
"    var cssSelector = cssSelectorFactory.getSelector(evt.target),\n" +
"        code = eventCodingMap.getEventCode(evt),\n" +
"        coordinates = eventCoordinators.getClientCoordinates(evt);\n" +
"\n" +
"    if (code) {\n" +
"        return code + ',' + cssSelector + ',' + JSON.stringify(coordinates);\n" +
"    }\n" +
"\n" +
"    return evt.type + ',' + cssSelector + ',' + JSON.stringify(coordinates);\n" +
"}\n" +
"\n" +
"module.exports = {\n" +
"    generateCode: generateCode\n" +
"};\n" +
"},{\"./css-selector-factory\":3,\"./event-coding-map\":6,\"./event-coordinates\":7}],3:[function(require,module,exports){\n" +
"/*jslint nomen: true*/\n" +
"/*global $,define,require,module */\n" +
"\n" +
"var dom = require('./dom');\n" +
"\n" +
"function getIdOrCls(el) {\n" +
"    if (el.id) {\n" +
"        return '#' + el.id;\n" +
"    } else if (el.classList && el.classList.length > 0) {\n" +
"        return '.' + el.className.split(' ').join('.');\n" +
"    }\n" +
"    return '';\n" +
"}\n" +
"\n" +
"function getCssSelector(el) {\n" +
"    var selectorList = ['', ''],\n" +
"        selector,\n" +
"        parentEl;\n" +
"\n" +
"    selectorList[1] = getIdOrCls(el);\n" +
"\n" +
"    if (el.id) {\n" +
"        return selectorList[1];\n" +
"    }\n" +
"\n" +
"    if (selectorList[1].length === 0) {\n" +
"        selector = el.nodeName;\n" +
"\n" +
"        if (selector === 'A') {\n" +
"            selector += ':contains(' + el.textContent + ')'\n" +
"        }\n" +
"        selectorList[1] = selector;\n" +
"    }\n" +
"\n" +
"    parentEl = dom.up(el, function (element) {\n" +
"        return getIdOrCls(element).length > 0;\n" +
"    });\n" +
"\n" +
"    selectorList[0] = getIdOrCls(parentEl);\n" +
"\n" +
"    return selectorList.join(' ').trim();\n" +
"}\n" +
"\n" +
"module.exports = {\n" +
"    getSelector: getCssSelector\n" +
"};\n" +
"},{\"./dom\":5}],4:[function(require,module,exports){\n" +
"/*jslint nomen: true*/\n" +
"/*global $,define,require,module */\n" +
"\n" +
"function isEnterText(evt) {\n" +
"    var element = evt.target;\n" +
"    return (element.type === 'text' || element.type === 'textarea') && evt.type === 'keyup';\n" +
"}\n" +
"\n" +
"function getCustomEventType(evt) {\n" +
"    if (isEnterText(evt)) {\n" +
"        return 'enterText';\n" +
"    }\n" +
"}\n" +
"\n" +
"module.exports = {\n" +
"    getType: getCustomEventType\n" +
"};\n" +
"},{}],5:[function(require,module,exports){\n" +
"/*jslint nomen: true*/\n" +
"/*global $,define,require,module */\n" +
"\n" +
"function up(el, stopCondition) {\n" +
"    var target = el;\n" +
"\n" +
"    while (target.parentNode) {\n" +
"        target = target.parentNode;\n" +
"        if (stopCondition(target)) {\n" +
"            break;\n" +
"        }\n" +
"    }\n" +
"    return target;\n" +
"}\n" +
"\n" +
"module.exports = {\n" +
"    up: up\n" +
"};\n" +
"},{}],6:[function(require,module,exports){\n" +
"/*jslint nomen: true*/\n" +
"/*global $,define,require,module */\n" +
"\n" +
"var customEvent = require('./custom-event'),\n" +
"    codingMap = {\n" +
"        click: '.waitAndClick',\n" +
"        enterText: '.typeValue' // this is a non-existing event to represent type in values to a textbox or textarea\n" +
"    };\n" +
"\n" +
"function getEventCode(evt) {\n" +
"    var code = codingMap[evt.type];\n" +
"\n" +
"    if (code) {\n" +
"        return code;\n" +
"    }\n" +
"\n" +
"    // handle non-existing events\n" +
"    return codingMap[customEvent.getType(evt)];\n" +
"}\n" +
"\n" +
"module.exports = {\n" +
"    getEventCode: getEventCode\n" +
"};\n" +
"},{\"./custom-event\":4}],7:[function(require,module,exports){\n" +
"/*jslint nomen: true*/\n" +
"/*global $,define,require,module */\n" +
"\n" +
"var eventsWithCoordinates = {\n" +
"    mouseup: true,\n" +
"    mousedown: true,\n" +
"    mousemove: true,\n" +
"    mouseover: true\n" +
"};\n" +
"\n" +
"function getClientCoordinates(evt) {\n" +
"    if (!eventsWithCoordinates[evt.type]) {\n" +
"        return '';\n" +
"    }\n" +
"\n" +
"    return {\n" +
"        x: evt.clientX,\n" +
"        y: evt.clientY\n" +
"    };\n" +
"}\n" +
"\n" +
"module.exports = {\n" +
"    getClientCoordinates: getClientCoordinates\n" +
"};\n" +
"},{}],8:[function(require,module,exports){\n" +
"/*jslint nomen: true*/\n" +
"/*global $,define,require,module */\n" +
"\n" +
"module.exports = [\n" +
"    'click',\n" +
"//    'focus',\n" +
"//    'blur',\n" +
"    'dblclick',\n" +
"    'change',\n" +
"    'keyup',\n" +
"//    'keydown',\n" +
"//    'keypress',\n" +
"    'mousedown',\n" +
"//    'mousemove',\n" +
"//    'mouseout',\n" +
"//    'mouseover',\n" +
"//    'mouseup',\n" +
"    'resize',\n" +
"//    'scroll',\n" +
"    'select',\n" +
"    'submit',\n" +
"    'load',\n" +
"    'unload'\n" +
"];\n" +
"\n" +
"//var events = [\n" +
"//    abort,\n" +
"//    afterprint,\n" +
"//    beforeprint,\n" +
"//    beforeunload,\n" +
"//    blur,\n" +
"//    canplay,\n" +
"//    canplaythrough,\n" +
"//    change,\n" +
"//    click,\n" +
"//    contextmenu,\n" +
"//    copy,\n" +
"//    cuechange,\n" +
"//    cut,\n" +
"//    dblclick,\n" +
"//    DOMContentLoaded,\n" +
"//    drag,\n" +
"//    dragend,\n" +
"//    dragenter,\n" +
"//    dragleave,\n" +
"//    dragover,\n" +
"//    dragstart,\n" +
"//    drop,\n" +
"//    durationchange,\n" +
"//    emptied,\n" +
"//    ended,\n" +
"//    error,\n" +
"//    focus,\n" +
"//    focusin,\n" +
"//    focusout,\n" +
"//    formchange,\n" +
"//    forminput,\n" +
"//    hashchange,\n" +
"//    input,\n" +
"//    invalid,\n" +
"//    keydown,\n" +
"//    keypress,\n" +
"//    keyup,\n" +
"//    load,\n" +
"//    loadeddata,\n" +
"//    loadedmetadata,\n" +
"//    loadstart,\n" +
"//    message,\n" +
"//    mousedown,\n" +
"//    mouseenter,\n" +
"//    mouseleave,\n" +
"//    mousemove,\n" +
"//    mouseout,\n" +
"//    mouseover,\n" +
"//    mouseup,\n" +
"//    mousewheel,\n" +
"//    offline,\n" +
"//    online,\n" +
"//    pagehide,\n" +
"//    pageshow,\n" +
"//    paste,\n" +
"//    pause,\n" +
"//    play,\n" +
"//    playing,\n" +
"//    popstate,\n" +
"//    progress,\n" +
"//    ratechange,\n" +
"//    readystatechange,\n" +
"//    redo,\n" +
"//    reset,\n" +
"//    resize,\n" +
"//    scroll,\n" +
"//    seeked,\n" +
"//    seeking,\n" +
"//    select,\n" +
"//    show,\n" +
"//    stalled,\n" +
"//    storage,\n" +
"//    submit,\n" +
"//    suspend,\n" +
"//    timeupdate,\n" +
"//    undo,\n" +
"//    unload,\n" +
"//    volumechange,\n" +
"//    waiting\n" +
"//];\n" +
"},{}],9:[function(require,module,exports){\n" +
"/*jslint nomen: true*/\n" +
"/*global $,define,require,module */\n" +
"\n" +
"// var recordedCode = '',\n" +
"var recordedCode = [],\n" +
"    generateCode,\n" +
"    generateObject,\n" +
"    eventsToRecord,\n" +
"    windowToListen;\n" +
"\n" +
"function init(config) {\n" +
"    generateCode = config.generateCode;\n" +
"    generateObject = config.generateObject;\n" +
"    eventsToRecord = config.eventsToRecord;\n" +
"}\n" +
"\n" +
"function setWindowToListen(windowElement) {\n" +
"    windowToListen = windowElement;\n" +
"}\n" +
"\n" +
"// Each frame is a window\n" +
"function getAllFrames(windowElement, allFrames) {\n" +
"    try{\n" +
"        allFrames.push(windowElement.frames);\n" +
"        for (var i = 0; i < windowElement.frames.length; i++) {\n" +
"            getAllFrames(windowElement.frames[i], allFrames);\n" +
"        }\n" +
"        return allFrames;\n" +
"    }catch(err){\n" +
"        console.error('There is error in getting frame')\n" +
"    }\n" +
"   \n" +
"}\n" +
"\n" +
"function getElementsToListen(windowElement) {\n" +
"    return getAllFrames(windowElement, []);\n" +
"}\n" +
"\n" +
"function bind(el, eventType, handler) {\n" +
"    try{\n" +
"        if (el.addEventListener) { // DOM Level 2 browsers\n" +
"            el.addEventListener(eventType, handler, false);\n" +
"        } else if (el.attachEvent) { // IE <= 8\n" +
"            el.attachEvent('on' + eventType, handler);\n" +
"        } else { // ancient browsers\n" +
"            el['on' + eventType] = handler;\n" +
"        }\n" +
"    }catch(err){\n" +
"        console.error('error in bind',err);\n" +
"    }\n" +
"    \n" +
"}\n" +
"\n" +
"function unbind(el, eventType, handler) {\n" +
"    if (el.removeEventListener) {\n" +
"        el.removeEventListener(eventType, handler, false);\n" +
"    } else if (el.detachEvent) {\n" +
"        el.detachEvent(\"on\" + eventType, handler);\n" +
"    } else {\n" +
"        el[\"on\" + eventType] = null;\n" +
"    }\n" +
"}\n" +
"\n" +
"function manageSingleElementEvents(element, action, events, handler) {\n" +
"    var eventIndex = 0,\n" +
"        eventCount = events.length;\n" +
"\n" +
"    for (; eventIndex < eventCount; eventIndex++) {\n" +
"        action(element, events[eventIndex], handler);\n" +
"    }\n" +
"}\n" +
"\n" +
"function manageEvents(elements, action, events, handler) {\n" +
"    var elementIndex = 0,\n" +
"        elementCount = elements.length;\n" +
"\n" +
"    for (; elementIndex < elementCount; elementIndex++) {\n" +
"        // Have to attach events with some delay between iframes. Otherwise, iframe events are not captured\n" +
"        setTimeout(manageSingleElementEvents, 50, elements[elementIndex], action, events, handler);\n" +
"    }\n" +
"}\n" +
"\n" +
"function recordEvent(e) {\n" +
"    var code;\n" +
"\n" +
"    if (generateObject) {\n" +
"        console.recorderLog(JSON.stringify(generateObject(e), true, 2));\n" +
"    }\n" +
"    if (generateCode) {\n" +
"        code = [generateCode(e)];\n" +
"\n" +
"        // recordedCode = recordedCode + code + '\\n';\n" +
"        recordedCode.push(code);\n" +
"        console.recorderLog(code);\n" +
"    }\n" +
"}\n" +
"\n" +
"function record() {\n" +
"    var elementsToListen = getElementsToListen(windowToListen || window);\n" +
"    console.recorderLog = console.log; // hijack the console.log so that only recorded code will be shown\n" +
"    console.log = function () {};\n" +
"    manageEvents(elementsToListen, bind, eventsToRecord, recordEvent);\n" +
"}\n" +
"\n" +
"function stop() {\n" +
"    var elementsToListen = getElementsToListen(windowToListen || window);\n" +
"    if (console.recorderLog) {\n" +
"        console.log = console.recorderLog;\n" +
"    }\n" +
"    manageEvents(elementsToListen, unbind, eventsToRecord, recordEvent);\n" +
"}\n" +
"\n" +
"// function getRecordedCode() {\n" +
"//     return recordedCode;\n" +
"// }\n" +
"/* generate Excel */\n" +
"function getRecordedCode() {\n" +
"    url = toExcelUrl(recordedCode);\n" +
"    // Create link.\n" +
"    a = document.createElement( \"a\" );\n" +
"    // Set link on DOM.\n" +
"    document.body.appendChild( a );\n" +
"    // Set link's visibility.\n" +
"    a.style = \"display: none\";\n" +
"    // Set href on link.\n" +
"    a.href = url;\n" +
"    // Set file name on link.\n" +
"    a.download = \"report.xls\";//file name can be passed\n" +
"    // Trigger click of link.\n" +
"    a.click();\n" +
"    // Clear.\n" +
"    window.URL.revokeObjectURL( url );\n" +
"    // return recordedCode;\n" +
"}\n" +
"\n" +
"function clearRecordedCode() {\n" +
"    return recordedCode = '';\n" +
"}\n" +
"function toExcelUrl(data) {\n" +
"     console.log(data);\n" +
"    var tsv = data.map(function(row) { console.log(row); return  Array.prototype.join.call(row,'\\t'); }).join('\\n');\n" +
"    var blob = new Blob([tsv], {type:'application/vnd.ms-excel'});\n" +
"    return URL.createObjectURL(blob);\n" +
"}\n" +
"module.exports = {\n" +
"    init: init,\n" +
"    setWindowToListen: setWindowToListen,\n" +
"    record: record,\n" +
"    stop: stop,\n" +
"    getRecordedCode: getRecordedCode,\n" +
"    clearRecordedCode: clearRecordedCode\n" +
"};\n" +
"\n" +
"},{}]},{},[1])");
        } catch (Exception e) {
            if (dr != null) {
                dr.quit();
            }
        }
        
//        return dr;
    }
    
    /**
     * function : startRecordAction
     * description : starts Recording actions
     */
    public void startRecordAction(){
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//         System.setProperty("webdriver.chrome.driver", "/Users/nvlakshmidurgab/Downloads/chromedriver");
//        System.out.println("open browser");
//        // TODO code application logic here
//       BrowserActionCapture b = new BrowserActionCapture();
//       b.openBrowserWithUrl();
//         b.invokeGeckoFirefox(true);
//       b.captureBrowserAction();
//       b.captureBrowserAction();
    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OldFrame().setVisible(true);
            }
     });


    }
    
    
}
