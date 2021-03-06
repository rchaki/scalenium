package com.markatta.scalenium

import collection.JavaConversions._
import org.openqa.selenium._
import org.openqa.selenium.interactions.Actions


trait MarkupSearch { this: HasDriver with HasSearchContext =>

  /** @return all matching elements or empty sequence if not found or any other type of error */
  def find(cssSelector: String): Seq[Element] =
    try {
      searchContext.findElements(By.cssSelector(cssSelector)).map(e => new Element(cssSelector, driver, e))
    } catch {
      case e: RuntimeException => Seq()
    }

  // aliases for find
  def select(cssSelector: String) = find(cssSelector)
  def all(cssSelector: String) = find(cssSelector)

  // TODO: figure out a way to keep the selector in the option so that we can say what selection failed on None.get
  def first(cssSelector: String): Option[Element] = find(cssSelector).headOption

}


