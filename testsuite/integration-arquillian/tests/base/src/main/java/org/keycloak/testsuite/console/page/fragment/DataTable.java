/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keycloak.testsuite.console.page.fragment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.keycloak.testsuite.util.WaitUtils.pause;
import static org.keycloak.testsuite.util.WaitUtils.waitUntilElement;
import org.openqa.selenium.By;
import static org.openqa.selenium.By.xpath;

/**
 *
 * @author tkyjovsk
 */
public class DataTable {

    @FindBy(css = "input[class*='search']")
    private WebElement searchInput;
    @FindBy(css = "div[class='input-group-addon'] i")
    private WebElement searchButton;

    @FindBy(tagName = "thead")
    private WebElement header;
    @FindBy(css = "tbody")
    private WebElement body;
    @FindBy(xpath = "(//table)[1]/tbody/tr[@class='ng-scope']")
    private List<WebElement> rows;
    
    @FindBy
    private WebElement infoRow;

    public void search(String pattern) {
        waitForBody();
        searchInput.sendKeys(pattern);
        searchButton.click();
    }

    public void clickHeaderButton(String buttonText) {
        waitForBody();
        header.findElement(By.xpath(".//button[text()='" + buttonText + "']")).click();
    }

    public void clickHeaderLink(String linkText) {
        waitForBody();
        header.findElement(By.linkText(linkText)).click();
    }

    public WebElement body() {
        return body;
    }

    public void waitForBody() {
        waitUntilElement(body).is().present();
    }

    public List<WebElement> rows() {
        waitForBody();
        pause(250);
        return rows;
    }

    public WebElement getRowByLinkText(String text) {
        WebElement row = body.findElement(By.xpath(".//tr[./td/a[text()='" + text + "']]"));
        waitUntilElement(row).is().present();
        return row;
    }

    public void clickRowByLinkText(String text) {
        body.findElement(By.xpath(".//tr/td/a[text()='" + text + "']")).click();
    }

    public void clickRowActionButton(WebElement row, String buttonText) {
        row.findElement(xpath(".//button[text()='" + buttonText + "']")).click();
    }
    
}
