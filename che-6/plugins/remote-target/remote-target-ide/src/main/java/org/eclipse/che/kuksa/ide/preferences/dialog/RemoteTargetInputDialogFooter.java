/*
 * Copyright (c) 2018
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Pedro Cuadra - pjcuadra@gmail.com
 */
package org.eclipse.che.kuksa.ide.preferences.dialog;

import static org.eclipse.che.kuksa.ide.preferences.dialog.RemoteTargetInputDialogView.ActionDelegate;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import javax.validation.constraints.NotNull;
import org.eclipse.che.ide.ui.UILocalizationConstant;
import org.eclipse.che.ide.ui.window.WindowClientBundle;

/**
 * The footer show on input dialogs.
 *
 * @author Pedro Cuadra
 */
public class RemoteTargetInputDialogFooter implements IsWidget {

  protected final WindowClientBundle resources;
  /** The UI binder instance. */
  private static ConfirmWindowFooterUiBinder uiBinder =
      GWT.create(ConfirmWindowFooterUiBinder.class);
  /** The i18n messages. */
  @UiField(provided = true)
  UILocalizationConstant messages;

  @UiField protected Button okButton;
  @UiField protected Button cancelButton;

  protected HTMLPanel rootPanel;

  /** The action delegate. */
  private ActionDelegate actionDelegate;

  @Inject
  public RemoteTargetInputDialogFooter(
      final @NotNull UILocalizationConstant messages, WindowClientBundle resources) {
    this.messages = messages;
    this.resources = resources;
    rootPanel = uiBinder.createAndBindUi(this);

    okButton.addStyleName(resources.getStyle().windowFrameFooterButtonPrimary());
    okButton.getElement().setId("askValue-dialog-ok");

    cancelButton.addStyleName(resources.getStyle().windowFrameFooterButton());
    cancelButton.getElement().setId("askValue-dialog-cancel");
  }

  /**
   * Sets the action delegate.
   *
   * @param delegate the new value
   */
  public void setDelegate(final ActionDelegate delegate) {
    this.actionDelegate = delegate;
  }

  /**
   * Handler set on the OK button.
   *
   * @param event the event that triggers the handler call
   */
  @UiHandler("okButton")
  public void handleOkClick(final ClickEvent event) {
    this.actionDelegate.accepted();
  }

  /**
   * Handler set on the cancel button.
   *
   * @param event the event that triggers the handler call
   */
  @UiHandler("cancelButton")
  public void handleCancelClick(final ClickEvent event) {
    this.actionDelegate.cancelled();
  }

  Button getOkButton() {
    return okButton;
  }

  @Override
  public Widget asWidget() {
    return rootPanel;
  }

  /** The UI binder interface for this component. */
  interface ConfirmWindowFooterUiBinder
      extends UiBinder<HTMLPanel, RemoteTargetInputDialogFooter> {}
}
