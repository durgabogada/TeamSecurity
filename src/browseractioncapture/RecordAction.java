/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package browseractioncapture;

import java.io.FileInputStream;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 *
 * @author nvlakshmidurgab
 */
public class RecordAction {

    String url;
    String driverPath;
    String browserType;
    String fileName;
    ChromeDriver chromeDriver = null;
    FirefoxDriver fireFoxDriver = null;
    AutomateScript ac = new AutomateScript();

    public RecordAction() {
    }

    public RecordAction(String url, String driverPath, String browserType) {

        this.url = url;
        this.driverPath = driverPath;
        this.browserType = browserType;
        System.out.println("url:" + url);
        System.out.println("driverPath:" + driverPath);
        System.out.println("browserType:" + browserType);
    }

    public void openBrowser() {

        if (this.browserType == "Chrome") {
            // call selenium driver to open chrome browser 
            System.setProperty("webdriver.chrome.driver", this.driverPath);
            chromeDriver = new ChromeDriver();
            chromeDriver.get(this.url);
            chromeDriver.executeScript(getScript());
        } else if (this.browserType == "FireFox") {
            // call sennium driver to open firefox browser
            System.setProperty("webdriver.gecko.driver", this.driverPath);
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            FirefoxProfile profile = new FirefoxProfile();
            firefoxOptions.setCapability(FirefoxDriver.PROFILE, profile);
            firefoxOptions.setCapability("marionette", true);
            try {
                fireFoxDriver = new FirefoxDriver(firefoxOptions);
                fireFoxDriver.get(this.url);
            } catch (Exception e) {
                if (fireFoxDriver != null) {
                    fireFoxDriver.quit();
                }
            }
        }
    }

    public void startRecord() {
        if (this.browserType == "Chrome") {
//            if(this.url != this.chromeDriver.getCurrentUrl()){
//                 this.chromeDriver.executeScript(getScript());
//            }
//           this.chromeDriver.executeScript(
//                   "    var div = document.createElement('div');\n"+
//                   "    div.innerHTML = 'Recording Actions';\n" +
//                   "    div.style.width = 120px;\n" +
//                   "    div.style.height = 40px;\n" +
//                   "    div.style.margin = 0 auto;\n" +
//                   "    div.style.background-color = blue;\n" +
//                   "    div.style.text-align = center;\n" +
//                   "    div.style.width = 120px;\n" +
//                   "    div.style.font-size = 30px;\n" +
//                   "    div.style.z-index = 9999;\n" +
//                   "    document.body.prepend(div);\n" +
//                 "}\n");

            this.chromeDriver.executeScript("recorder.record();");
            System.out.println("url" + this.url);
            System.out.println("current url" + this.chromeDriver.getCurrentUrl());
//             
        } else if (this.browserType == "FireFox") {
            this.fireFoxDriver.executeScript("recorder.record();");
        }
    }

    public void stopRecord() {
        if (this.browserType == "Chrome") {
//            if(this.url != this.chromeDriver.getCurrentUrl()){
//                 this.chromeDriver.executeScript(getScript());
//            }
            this.chromeDriver.executeScript("alert('Stopping Record')");
            this.chromeDriver.executeScript(getScript());
            this.chromeDriver.executeScript("recorder.stop()");
        } else if (this.browserType == "FireFox") {
            this.fireFoxDriver.executeScript("recorder.stop()");
        }
    }

    public void saveRecord(String fileName) {
        this.fileName = fileName;
        System.out.println("the fileName" + this.fileName);
        if (this.browserType == "Chrome") {
//            if(this.url != this.chromeDriver.getCurrentUrl()){
//                 this.chromeDriver.executeScript(getScript());
//            }
//            this.chromeDriver.executeScript("setTimeOut(function(){alert('Action is saved..')},2000)");
            this.chromeDriver.executeScript(getScript());
            this.chromeDriver.executeScript("recorder.getRecordedCode()");
        } else if (this.browserType == "FireFox") {
            this.fireFoxDriver.executeScript("recorder.getRecordedCode();");
        }
    }

    /**
     * function : playRecordedScript description :
     *
     * @return
     */
    public String playRecordedScript(String excelFilePath, String browserType, String driverPath) {
//        this.browserType = browserType;
        String response = null;
        if (browserType == "Chrome") {
            try {
                System.out.println("the fileName" + excelFilePath);
                System.setProperty("webdriver.chrome.driver", driverPath);
//                WebDriver dr = new ChromeDriver();
                ChromeDriver dr = new ChromeDriver();
                String filePath = excelFilePath;
                FileInputStream inputStream = new FileInputStream(filePath);
                Workbook wb = new HSSFWorkbook(inputStream);
                System.out.println(wb);
                Sheet firstSheet = wb.getSheet("Sheet1");
                System.out.println(firstSheet);
                int rowCount = firstSheet.getLastRowNum() - firstSheet.getFirstRowNum();
                System.out.println("rowCount" + rowCount);
                //Create a loop over all the rows of excel file to read it
                ArrayList<String> listOfValues = new ArrayList<>();
                for (int i = 0; i < rowCount + 1; i++) {
                    Row row = firstSheet.getRow(i);
                    //Create a loop to print cell values in a row
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        //Print Excel data in console
                        listOfValues.add(row.getCell(j).getStringCellValue());
                    }
                }
                response = AutomateScript.scriptExecute(listOfValues, browserType);
                //
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (browserType == "FireFox") {
            try {
                System.out.println("the fileName" + excelFilePath);
                System.setProperty("webdriver.gecko.driver", driverPath);
//                WebDriver dr = new ChromeDriver();
                FirefoxDriver dr = new FirefoxDriver();
                String filePath = excelFilePath;
                FileInputStream inputStream = new FileInputStream(filePath);
                Workbook wb = new HSSFWorkbook(inputStream);
                System.out.println(wb);
                Sheet firstSheet = wb.getSheet("Sheet1");
                System.out.println(firstSheet);
                int rowCount = firstSheet.getLastRowNum() - firstSheet.getFirstRowNum();
                System.out.println("rowCount" + rowCount);
                //Create a loop over all the rows of excel file to read it
                ArrayList<String> listOfValues = new ArrayList<>();
                for (int i = 0; i < rowCount + 1; i++) {
                    Row row = firstSheet.getRow(i);
                    //Create a loop to print cell values in a row
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        //Print Excel data in console
                        listOfValues.add(row.getCell(j).getStringCellValue());
                    }
                }
                AutomateScript.scriptExecute(listOfValues, browserType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;

    }

    public String getScript() {
        return "(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require==\"function\"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error(\"Cannot find module '\"+o+\"'\")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require==\"function\"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){\n"
                + "/*jslint nomen: true*/\n"
                + "/*global $,define,require,module */\n"
                + "\n"
                + "var recorder = require('./recorder');\n"
                + "var eventsToRecord = require('./events-to-record');\n"
                + "var codeGenerator = require('./code-generator-css');\n"
                + "\n"
                + "recorder.init({\n"
                + "    generateCode: codeGenerator.generateCode,\n"
                + "    eventsToRecord: eventsToRecord\n"
                + "});\n"
                + "window.recorder = recorder;\n"
                + "module.exports = recorder;\n"
                + "},{\"./code-generator-css\":2,\"./events-to-record\":8,\"./recorder\":9}],2:[function(require,module,exports){\n"
                + "/*jslint nomen: true*/\n"
                + "/*global $,define,require,module */\n"
                + "\n"
                + "var eventCodingMap = require('./event-coding-map'),\n"
                + "    cssSelectorFactory = require('./css-selector-factory'),\n"
                + "    eventCoordinators = require('./event-coordinates');\n"
                + "\n"
                + "// function generateCode(evt) {\n"
                + "//     var cssSelector = cssSelectorFactory.getSelector(evt.target),\n"
                + "//         code = eventCodingMap.getEventCode(evt),\n"
                + "//         coordinates = eventCoordinators.getClientCoordinates(evt);\n"
                + "\n"
                + "//     if (code) {\n"
                + "//         return code + '(\\'' + cssSelector + '\\', ' + JSON.stringify(coordinates) + ')';\n"
                + "//     }\n"
                + "\n"
                + "//     return evt.type + ' \\'' + cssSelector + '\\' ' + JSON.stringify(coordinates);\n"
                + "// }\n"
                + "function generateCode(evt) {\n"
                + "    var cssSelector = cssSelectorFactory.getSelector(evt.target),\n"
                + "        code = eventCodingMap.getEventCode(evt),\n"
                + "        coordinates = eventCoordinators.getClientCoordinates(evt);\n"
                + "\n"
                + "    if (code) {\n"
                + "        return code + ',' + cssSelector + ',' + JSON.stringify(coordinates) + ']';\n"
                + "    }\n"
                + "\n"
                + "    return evt.type + ',' + cssSelector + ',' + JSON.stringify(coordinates) + ']';\n"
                + "}\n"
                + "\n"
                + "module.exports = {\n"
                + "    generateCode: generateCode\n"
                + "};\n"
                + "},{\"./css-selector-factory\":3,\"./event-coding-map\":6,\"./event-coordinates\":7}],3:[function(require,module,exports){\n"
                + "/*jslint nomen: true*/\n"
                + "/*global $,define,require,module */\n"
                + "\n"
                + "var dom = require('./dom');\n"
                + "\n"
                + "function getIdOrCls(el) {\n"
                + "    if (el.id) {\n"
                + "        return '#' + el.id;\n"
                + "    } else if (el.classList && el.classList.length > 0) {\n"
                + "        return '.' + el.className.split(' ').join('.');\n"
                + "    }\n"
                + "    return '';\n"
                + "}\n"
                + "\n"
                + "function getCssSelector(el) {\n"
                + "    var selectorList = ['', ''],\n"
                + "        selector,\n"
                + "        parentEl;\n"
                + "\n"
                + "    selectorList[1] = getIdOrCls(el);\n"
                + "\n"
                + "    if (el.id) {\n"
                + "        return selectorList[1];\n"
                + "    }\n"
                + "\n"
                + "    if (selectorList[1].length === 0) {\n"
                + "        selector = el.nodeName;\n"
                + "\n"
                + "        if (selector === 'A') {\n"
                + "            selector += ':contains(' + el.textContent + ')'\n"
                + "        }\n"
                + "        selectorList[1] = selector;\n"
                + "    }\n"
                + "\n"
                + "    parentEl = dom.up(el, function (element) {\n"
                + "        return getIdOrCls(element).length > 0;\n"
                + "    });\n"
                + "\n"
                + "    selectorList[0] = getIdOrCls(parentEl);\n"
                + "\n"
                + "    return selectorList.join(' ').trim();\n"
                + "}\n"
                + "\n"
                + "module.exports = {\n"
                + "    getSelector: getCssSelector\n"
                + "};\n"
                + "},{\"./dom\":5}],4:[function(require,module,exports){\n"
                + "/*jslint nomen: true*/\n"
                + "/*global $,define,require,module */\n"
                + "\n"
                + "function isEnterText(evt) {\n"
                + "    var element = evt.target;\n"
                + "    return (element.type === 'text' || element.type === 'textarea') && evt.type === 'keyup';\n"
                + "}\n"
                + "\n"
                + "function getCustomEventType(evt) {\n"
                + "    if (isEnterText(evt)) {\n"
                + "        return 'enterText';\n"
                + "    }\n"
                + "}\n"
                + "\n"
                + "module.exports = {\n"
                + "    getType: getCustomEventType\n"
                + "};\n"
                + "},{}],5:[function(require,module,exports){\n"
                + "/*jslint nomen: true*/\n"
                + "/*global $,define,require,module */\n"
                + "\n"
                + "function up(el, stopCondition) {\n"
                + "    var target = el;\n"
                + "\n"
                + "    while (target.parentNode) {\n"
                + "        target = target.parentNode;\n"
                + "        if (stopCondition(target)) {\n"
                + "            break;\n"
                + "        }\n"
                + "    }\n"
                + "    return target;\n"
                + "}\n"
                + "\n"
                + "module.exports = {\n"
                + "    up: up\n"
                + "};\n"
                + "},{}],6:[function(require,module,exports){\n"
                + "/*jslint nomen: true*/\n"
                + "/*global $,define,require,module */\n"
                + "\n"
                + "var customEvent = require('./custom-event'),\n"
                + //"    codingMap = {\n" +
                //"        click: '.waitAndClick',\n" +
                //"        enterText: '.typeValue' // this is a non-existing event to represent type in values to a textbox or textarea\n" +
                //"    };\n" +
                "    codingMap = {\n"
                + "        click: 'click',\n"
                + "        enterText: 'enterText' // this is a non-existing event to represent type in values to a textbox or textarea\n"
                + "    };\n"
                + "\n"
                + "function getEventCode(evt) {\n"
                + "    var code = codingMap[evt.type];\n"
                + "\n"
                + "    if (code) {\n"
                + "        return code;\n"
                + "    }\n"
                + "\n"
                + "    // handle non-existing events\n"
                + "    return codingMap[customEvent.getType(evt)];\n"
                + "}\n"
                + "\n"
                + "module.exports = {\n"
                + "    getEventCode: getEventCode\n"
                + "};\n"
                + "},{\"./custom-event\":4}],7:[function(require,module,exports){\n"
                + "/*jslint nomen: true*/\n"
                + "/*global $,define,require,module */\n"
                + "\n"
                + "var eventsWithCoordinates = {\n"
                + "    mouseup: true,\n"
                + "    mousedown: true,\n"
                + "    mousemove: true,\n"
                + "    mouseover: true\n"
                + "};\n"
                + "\n"
                + "function getClientCoordinates(evt) {\n"
                + "    if (!eventsWithCoordinates[evt.type]) {\n"
                + "        return '';\n"
                + "    }\n"
                + "\n"
                + "    return {\n"
                + "        x: evt.clientX,\n"
                + "        y: evt.clientY\n"
                + "    };\n"
                + "}\n"
                + "\n"
                + "module.exports = {\n"
                + "    getClientCoordinates: getClientCoordinates\n"
                + "};\n"
                + "},{}],8:[function(require,module,exports){\n"
                + "/*jslint nomen: true*/\n"
                + "/*global $,define,require,module */\n"
                + "\n"
                + "module.exports = [\n"
                + "    'click',\n"
                + "//    'focus',\n"
                + "//    'blur',\n"
                + "    'dblclick',\n"
                + "    'change',\n"
                + "    'keyup',\n"
                + "//    'keydown',\n"
                + "//    'keypress',\n"
                + "    'mousedown',\n"
                + "//    'mousemove',\n"
                + "//    'mouseout',\n"
                + "//    'mouseover',\n"
                + "//    'mouseup',\n"
                + "    'resize',\n"
                + "//    'scroll',\n"
                + "    'select',\n"
                + "    'submit',\n"
                + "    'load',\n"
                + "    'unload'\n"
                + "];\n"
                + "\n"
                + "//var events = [\n"
                + "//    abort,\n"
                + "//    afterprint,\n"
                + "//    beforeprint,\n"
                + "//    beforeunload,\n"
                + "//    blur,\n"
                + "//    canplay,\n"
                + "//    canplaythrough,\n"
                + "//    change,\n"
                + "//    click,\n"
                + "//    contextmenu,\n"
                + "//    copy,\n"
                + "//    cuechange,\n"
                + "//    cut,\n"
                + "//    dblclick,\n"
                + "//    DOMContentLoaded,\n"
                + "//    drag,\n"
                + "//    dragend,\n"
                + "//    dragenter,\n"
                + "//    dragleave,\n"
                + "//    dragover,\n"
                + "//    dragstart,\n"
                + "//    drop,\n"
                + "//    durationchange,\n"
                + "//    emptied,\n"
                + "//    ended,\n"
                + "//    error,\n"
                + "//    focus,\n"
                + "//    focusin,\n"
                + "//    focusout,\n"
                + "//    formchange,\n"
                + "//    forminput,\n"
                + "//    hashchange,\n"
                + "//    input,\n"
                + "//    invalid,\n"
                + "//    keydown,\n"
                + "//    keypress,\n"
                + "//    keyup,\n"
                + "//    load,\n"
                + "//    loadeddata,\n"
                + "//    loadedmetadata,\n"
                + "//    loadstart,\n"
                + "//    message,\n"
                + "//    mousedown,\n"
                + "//    mouseenter,\n"
                + "//    mouseleave,\n"
                + "//    mousemove,\n"
                + "//    mouseout,\n"
                + "//    mouseover,\n"
                + "//    mouseup,\n"
                + "//    mousewheel,\n"
                + "//    offline,\n"
                + "//    online,\n"
                + "//    pagehide,\n"
                + "//    pageshow,\n"
                + "//    paste,\n"
                + "//    pause,\n"
                + "//    play,\n"
                + "//    playing,\n"
                + "//    popstate,\n"
                + "//    progress,\n"
                + "//    ratechange,\n"
                + "//    readystatechange,\n"
                + "//    redo,\n"
                + "//    reset,\n"
                + "//    resize,\n"
                + "//    scroll,\n"
                + "//    seeked,\n"
                + "//    seeking,\n"
                + "//    select,\n"
                + "//    show,\n"
                + "//    stalled,\n"
                + "//    storage,\n"
                + "//    submit,\n"
                + "//    suspend,\n"
                + "//    timeupdate,\n"
                + "//    undo,\n"
                + "//    unload,\n"
                + "//    volumechange,\n"
                + "//    waiting\n"
                + "//];\n"
                + "},{}],9:[function(require,module,exports){\n"
                + "/*jslint nomen: true*/\n"
                + "/*global $,define,require,module */\n"
                + "\n"
                + "// var recordedCode = '',\n"
                + "var recordedCode = [['url',document.URL,']']],\n"
                + "    generateCode,\n"
                + "    generateObject,\n"
                + "    eventsToRecord,\n"
                + "    windowToListen;\n"
                + "\n"
                + "function init(config) {\n"
                + "    generateCode = config.generateCode;\n"
                + "    generateObject = config.generateObject;\n"
                + "    eventsToRecord = config.eventsToRecord;\n"
                + "}\n"
                + "\n"
                + "function setWindowToListen(windowElement) {\n"
                + "    windowToListen = windowElement;\n"
                + "}\n"
                + "\n"
                + "// Each frame is a window\n"
                + "function getAllFrames(windowElement, allFrames) {\n"
                + "    try{\n"
                + "        allFrames.push(windowElement.frames);\n"
                + "        for (var i = 0; i < windowElement.frames.length; i++) {\n"
                + "            getAllFrames(windowElement.frames[i], allFrames);\n"
                + "        }\n"
                + "        return allFrames;\n"
                + "    }catch(err){\n"
                + "        console.error('There is error in getting frame')\n"
                + "    }\n"
                + "   \n"
                + "}\n"
                + "\n"
                + "function getElementsToListen(windowElement) {\n"
                + "    return getAllFrames(windowElement, []);\n"
                + "}\n"
                + "\n"
                + "function bind(el, eventType, handler) {\n"
                + "    try{\n"
                + "        if (el.addEventListener) { // DOM Level 2 browsers\n"
                + "            el.addEventListener(eventType, handler, false);\n"
                + "        } else if (el.attachEvent) { // IE <= 8\n"
                + "            el.attachEvent('on' + eventType, handler);\n"
                + "        } else { // ancient browsers\n"
                + "            el['on' + eventType] = handler;\n"
                + "        }\n"
                + "    }catch(err){\n"
                + "        console.error('error in bind',err);\n"
                + "    }\n"
                + "    \n"
                + "}\n"
                + "\n"
                + "function unbind(el, eventType, handler) {\n"
                + "    if (el.removeEventListener) {\n"
                + "        el.removeEventListener(eventType, handler, false);\n"
                + "    } else if (el.detachEvent) {\n"
                + "        el.detachEvent(\"on\" + eventType, handler);\n"
                + "    } else {\n"
                + "        el[\"on\" + eventType] = null;\n"
                + "    }\n"
                + "}\n"
                + "\n"
                + "function manageSingleElementEvents(element, action, events, handler) {\n"
                + "    var eventIndex = 0,\n"
                + "        eventCount = events.length;\n"
                + "\n"
                + "    for (; eventIndex < eventCount; eventIndex++) {\n"
                + "        action(element, events[eventIndex], handler);\n"
                + "    }\n"
                + "}\n"
                + "\n"
                + "function manageEvents(elements, action, events, handler) {\n"
                + "    var elementIndex = 0,\n"
                + "        elementCount = elements.length;\n"
                + "\n"
                + "    for (; elementIndex < elementCount; elementIndex++) {\n"
                + "        // Have to attach events with some delay between iframes. Otherwise, iframe events are not captured\n"
                + "        setTimeout(manageSingleElementEvents, 50, elements[elementIndex], action, events, handler);\n"
                + "    }\n"
                + "}\n"
                + "\n"
                + "function recordEvent(e) {\n"
                + "    var code;\n"
                + "\n"
                + "    if (generateObject) {\n"
                + "        console.recorderLog(JSON.stringify(generateObject(e), true, 2));\n"
                + "    }\n"
                + "    if (generateCode) {\n"
                + "        code = [generateCode(e)];\n"
                + "\n"
                + "        // recordedCode = recordedCode + code + '\\n';\n"
                + "        recordedCode.push(code);\n"
                + "   sessionStorage.setItem('recordedData',recordedCode);\n"
                + "        console.recorderLog(code);\n"
                + "    }\n"
                + "}\n"
                + "\n"
                + "function record() {\n"
                + "    var elementsToListen = getElementsToListen(windowToListen || window);\n"
                + "    console.recorderLog = console.log; // hijack the console.log so that only recorded code will be shown\n"
                + "    console.log = function () {};\n"
                + "    manageEvents(elementsToListen, bind, eventsToRecord, recordEvent);\n"
                + "}\n"
                + "\n"
                + "function stop() {\n"
                + "    var elementsToListen = getElementsToListen(windowToListen || window);\n"
                + "    if (console.recorderLog) {\n"
                + "        console.log = console.recorderLog;\n"
                + "    }\n"
                + "    manageEvents(elementsToListen, unbind, eventsToRecord, recordEvent);\n"
                + "}\n"
                + "\n"
                + "// function getRecordedCode() {\n"
                + "//     return recordedCode;\n"
                + "// }\n"
                + "/* generate Excel */\n"
                + "function getRecordedCode() {\n"
                + //"    url = toExcelUrl(recordedCode);\n" +
                "    url = toExcelUrl(sessionStorage.getItem('recordedData'));\n"
                + "    // Create link.\n"
                + "    a = document.createElement( \"a\" );\n"
                + "    // Set link on DOM.\n"
                + "    document.body.appendChild( a );\n"
                + "    // Set link's visibility.\n"
                + "    a.style = \"display: none\";\n"
                + "    // Set href on link.\n"
                + "    a.href = url;\n"
                + "    // Set file name on link.\n"
                + "    a.download = \"actions.xls\";//file name can be passed\n"
                + "    // Trigger click of link.\n"
                + "    a.click();\n"
                + "    // Clear.\n"
                + "    window.URL.revokeObjectURL( url );\n"
                + "    // return recordedCode;\n"
                + "}\n"
                + "\n"
                + "function clearRecordedCode() {\n"
                + "    return recordedCode = '';\n"
                + "}\n"
                + "function toExcelUrl(data) {\n"
                + " var dataArr = [];\n"
                + " var actionsArr = data.split(']') ;\n"
                + "    for (var i = 0;  i < actionsArr.length ;i++) {\n"
                + "       dataArr.push([actionsArr[i]]);\n"
                + "    }\n"
                + " dataArr.push(['url',document.URL]) ;\n"
                + " dataArr.push(['END']) ;\n"
                + "     console.log('dataArr',dataArr);\n"
                + "    var tsv = dataArr.map(function(row) { console.log(row); return  Array.prototype.join.call(row,'\\t'); }).join('\\n');\n"
                + "    var blob = new Blob([tsv], {type:'application/vnd.ms-excel'});\n"
                + "    return URL.createObjectURL(blob);\n"
                + "}\n"
                + "module.exports = {\n"
                + "    init: init,\n"
                + "    setWindowToListen: setWindowToListen,\n"
                + "    record: record,\n"
                + "    stop: stop,\n"
                + "    getRecordedCode: getRecordedCode,\n"
                + "    clearRecordedCode: clearRecordedCode\n"
                + "};\n"
                + "\n"
                + "},{}]},{},[1])";
    }

}
