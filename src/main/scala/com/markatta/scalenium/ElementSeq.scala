package com.markatta.scalenium

/**
 * Useful operations you might want to perform on a Seq of Element
 *
 * @param elements decorated seq
 */
class ElementSeq(elements: Seq[Element]) {

  /** Click on all elements on the list.
    * Only the visible elements are filled, if no elements are visible
    * nothing happens.
    */
  def click()(implicit failureHandler: MissingElementFailureHandler): Seq[Element] = {
    elements.foreach(_.click())
    this.elements
  }

  /** Clear all visible elements in the list,
    * if no elements are visible
    * nothing happens. */
  def clearAll()(implicit failureHandler: MissingElementFailureHandler): Seq[Element] = {
    elements.filter(_.visible).foreach(_.clear())
    this.elements
  }

  /** write the given text into all elements of the list */
  def write(text: String)(implicit failureHandler: MissingElementFailureHandler): Seq[Element] = {
    elements.foreach(_.write(text))
    this.elements
  }

  /** @return all element ids */
  def ids: Seq[String] = elements.map(_.id)

  /** @return all element text contents */
  def texts: Seq[String] = elements.map(_.text)
  /** @return the text content of all elements concatenated into one string */
  def text: String = elements.map(_.text).mkString
  def values: Seq[String] = elements.map(_.value)
  def html: String = elements.map(_.html).mkString

  /** @return true if all elements in the seq are enabled */
  def enabled = elements.forall(_.enabled)
  /** @return true if all elements in the seq are disabled */
  def disabled = elements.forall(_.disabled)

  def anyEnabled = elements.exists(_.enabled)
  def anyDisabled = elements.exists(_.disabled)

  /** @return true if all elements in the sequence are visible */
  def visible: Boolean = elements.forall(_.visible)
  /** @return true if all elements in the sequence are hidden */
  def hidden: Boolean = elements.forall(!_.hidden)

  def anyVisible: Boolean = elements.exists(_.visible)
  def anyHidden: Boolean = elements.exists(!_.visible)

  def anySelected = elements.exists(_.selected)

  def find(cssSelector: String): Seq[Element] = elements.flatMap(_.find(cssSelector))

}
