package part3.ch14;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class QuickEntryMediator {

    private final JTextField textField;
    private final JList list;

    public QuickEntryMediator(final JTextField textField, final JList list) {
        this.textField = textField;
        this.list = list;

        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(final DocumentEvent e) {
                textFieldChanged();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                textFieldChanged();
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
                textFieldChanged();
            }
        });
    }

    private void textFieldChanged() {
        final String prefix = textField.getText();
        if (prefix.length() == 0) {
            list.clearSelection();
            return;
        }
        final ListModel model = list.getModel();
        boolean found = false;
        for (int i = 0; found == false && i < model.getSize(); i++) {
            final Object element = model.getElementAt(i);
            final String s = element.toString();
            if (s.startsWith(prefix)) {
                list.setSelectedValue(element, true);
                found = true;
            }
        }
        if (!found) {
            list.clearSelection();
        }
    }
}
