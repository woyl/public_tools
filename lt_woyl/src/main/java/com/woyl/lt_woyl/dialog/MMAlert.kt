package com.woyl.lt_woyl.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import com.woyl.lt_woyl.R
import java.util.*

class MMAlert() {
    interface OnAlertSelectId {
        fun onClick (button : Int)
    }

    fun showAlert(
        context: Context?,
        msg: String?,
        title: String?
    ): AlertDialog? {
        if (context is Activity && context.isFinishing) {
            return null
        }
        val builder = AlertDialog.Builder(context)
        builder.setIcon(R.mipmap.ic_dialog_alert)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton(R.string.app_ok,
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        val alert = builder.create()
        alert.show()
        return alert
    }

    fun showAlert(
        context: Context?,
        msgId: Int,
        titleId: Int
    ): AlertDialog? {
        if (context is Activity && context.isFinishing) {
            return null
        }
        val builder = AlertDialog.Builder(context)
        builder.setIcon(R.mipmap.ic_dialog_alert)
        builder.setTitle(titleId)
        builder.setMessage(msgId)
        builder.setPositiveButton(R.string.app_ok,
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        val alert = builder.create()
        alert.show()
        return alert
    }

    fun showAlert(
        context: Context?,
        msgId: Int,
        titleId: Int,
        l: DialogInterface.OnClickListener?
    ): AlertDialog? {
        if (context is Activity && context.isFinishing) {
            return null
        }
        val builder = AlertDialog.Builder(context)
        builder.setIcon(R.mipmap.ic_dialog_alert)
        builder.setTitle(titleId)
        builder.setMessage(msgId)
        builder.setPositiveButton(R.string.app_ok, l)
        // builder.setCancelable(false);
        val alert = builder.create()
        alert.show()
        return alert
    }

    fun showAlert(
        context: Context?,
        msg: String?,
        title: String?,
        l: DialogInterface.OnClickListener?
    ): AlertDialog? {
        if (context is Activity && context.isFinishing) {
            return null
        }
        val builder = AlertDialog.Builder(context)
        builder.setIcon(R.mipmap.ic_dialog_alert)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton(R.string.app_ok, l)
        // builder.setCancelable(false);
        val alert = builder.create()
        alert.show()
        return alert
    }

    fun showAlert(
        context: Context?,
        msgId: Int,
        titleId: Int,
        lOk: DialogInterface.OnClickListener?,
        lCancel: DialogInterface.OnClickListener?
    ): AlertDialog? {
        if (context is Activity && context.isFinishing) {
            return null
        }
        val builder = AlertDialog.Builder(context)
        builder.setIcon(R.mipmap.ic_dialog_alert)
        builder.setTitle(titleId)
        builder.setMessage(msgId)
        builder.setPositiveButton(R.string.app_ok, lOk)
        builder.setNegativeButton(R.string.app_cancel, lCancel)
        // builder.setCancelable(false);
        val alert = builder.create()
        alert.show()
        return alert
    }

    fun showAlert(
        context: Context?,
        msg: Int,
        title: Int,
        yes: Int,
        no: Int,
        lOk: DialogInterface.OnClickListener?,
        lCancel: DialogInterface.OnClickListener?
    ): AlertDialog? {
        if (context is Activity && context.isFinishing) {
            return null
        }
        val builder = AlertDialog.Builder(context)
        builder.setIcon(R.mipmap.ic_dialog_alert)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton(yes, lOk)
        builder.setNegativeButton(no, lCancel)
        // builder.setCancelable(false);
        val alert = builder.create()
        alert.show()
        return alert
    }

    fun showAlert(
        context: Context?,
        msg: String?,
        title: String?,
        lOk: DialogInterface.OnClickListener?,
        lCancel: DialogInterface.OnClickListener?
    ): AlertDialog? {
        if (context is Activity && context.isFinishing) {
            return null
        }
        val builder = AlertDialog.Builder(context)
        builder.setIcon(R.mipmap.ic_dialog_alert)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton(R.string.app_ok, lOk)
        builder.setNegativeButton(R.string.app_cancel, lCancel)
        // builder.setCancelable(false);
        val alert = builder.create()
        alert.show()
        return alert
    }

    fun showAlert(
        context: Context?,
        msg: String?,
        title: String?,
        yes: String?,
        no: String?,
        lOk: DialogInterface.OnClickListener?,
        lCancel: DialogInterface.OnClickListener?
    ): AlertDialog? {
        if (context is Activity && context.isFinishing) {
            return null
        }
        val builder = AlertDialog.Builder(context)
        builder.setIcon(R.mipmap.ic_dialog_alert)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton(yes, lOk)
        builder.setNegativeButton(no, lCancel)
        // builder.setCancelable(false);
        val alert = builder.create()
        alert.show()
        return alert
    }

    fun showAlert(
        context: Context?,
        title: String?,
        view: View?,
        lOk: DialogInterface.OnClickListener?
    ): AlertDialog? {
        if (context is Activity && context.isFinishing) {
            return null
        }
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setView(view)
        builder.setPositiveButton(R.string.app_ok, lOk)
        // builder.setCancelable(true);
        val alert = builder.create()
        alert.show()
        return alert
    }

    fun showAlert(
        context: Context?,
        title: String?,
        view: View?,
        ok: String?,
        cancel: String?,
        lOk: DialogInterface.OnClickListener?,
        lCancel: DialogInterface.OnClickListener?
    ): AlertDialog? {
        if (context is Activity && context.isFinishing) {
            return null
        }
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setView(view)
        builder.setPositiveButton(ok, lOk)
        builder.setNegativeButton(cancel, lCancel)
        // builder.setCancelable(false);
        val alert = builder.create()
        alert.show()
        return alert
    }

    fun showAlert(
        context: Context?,
        title: String?,
        ok: String?,
        view: View?,
        lOk: DialogInterface.OnClickListener?
    ): AlertDialog? {
        if (context is Activity && context.isFinishing) {
            return null
        }
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setView(view)
        builder.setPositiveButton(ok, lOk)
        // builder.setCancelable(true);
        val alert = builder.create()
        alert.show()
        return alert
    }

    fun showAlert(
        context: Context?,
        title: String?,
        msg: String?,
        view: View?,
        lOk: DialogInterface.OnClickListener?,
        lCancel: DialogInterface.OnClickListener?
    ): AlertDialog? {
        if (context is Activity && context.isFinishing) {
            return null
        }
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setView(view)
        builder.setPositiveButton(R.string.app_ok, lOk)
        builder.setNegativeButton(R.string.app_cancel, lCancel)
        // builder.setCancelable(true);
        builder.setOnCancelListener { dialog -> lCancel?.onClick(dialog, 0) }
        val alert = builder.create()
        alert.show()
        return alert
    }

    fun showAlert(
        context: Context?,
        title: String?,
        view: View?,
        lCancel: DialogInterface.OnCancelListener?
    ): AlertDialog? {
        if (context is Activity && context.isFinishing) {
            return null
        }
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setView(view)
        // builder.setCancelable(true);
        builder.setOnCancelListener(lCancel)
        val alert = builder.create()
        alert.show()
        return alert
    }

    fun showAlert(
        context: Context,
        title: String?,
        items: Array<String>?,
        exit: String?,
        alertDo: OnAlertSelectId
    ): Dialog? {
        return showAlert(context, title, items, exit, alertDo, null)
    }

    private fun showAlert(
        context: Context,
        title: String?,
        items: Array<String>?,
        exit: String?,
        alertDo: OnAlertSelectId,
        nothing: Nothing?
    ): Dialog? {
        val cancel = context.getString(R.string.app_cancel)
        val dlg = Dialog(context, R.style.MMTheme_DataSheet)
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout =
            inflater.inflate(R.layout.alert_dialog_menu_layout, null) as LinearLayout
        val cFullFillWidth = 10000
        layout.minimumWidth = cFullFillWidth
        val list =
            layout.findViewById<View>(R.id.content_list) as ListView
        val adapter =
            AlertAdapter(context, title, items, exit, cancel)
        list.adapter = adapter
        list.dividerHeight = 0
        list.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                if (!(title == null || title == "") && position - 1 >= 0) {
                    alertDo.onClick(position - 1)
                    dlg.dismiss()
                    list.requestFocus()
                } else {
                    alertDo.onClick(position)
                    dlg.dismiss()
                    list.requestFocus()
                }
            }
        // set a large value put it in bottom
        val w = dlg.window
        val lp = w!!.attributes
        lp.x = 0
        val cMakeBottom = -1000
        lp.y = cMakeBottom
        lp.gravity = Gravity.BOTTOM
        dlg.onWindowAttributesChanged(lp)
        dlg.setCanceledOnTouchOutside(true)
        dlg.setContentView(layout)
        dlg.show()
        return dlg
    }

    fun showProgressDlg(
        context: Context?,
        title: String?,
        message: String?,
        indeterminate: Boolean,
        cancelable: Boolean,
        lCancel: DialogInterface.OnCancelListener?
    ): ProgressDialog? {
        return ProgressDialog.show(
            context,
            title,
            message,
            indeterminate,
            cancelable
        ) { dialog -> lCancel?.onCancel(dialog) }
    }

    /**
     * @param context
     * Context.
     * @param title
     * The title of this AlertDialog can be null .
     * @param items
     * button name list.
     * @param alertDo
     * methods call Id:Button + cancel_Button.
     * @param exit
     * Name can be null.It will be Red Color
     * @return A AlertDialog
     */
    fun showAlert(
        context: Context,
        title: String?,
        items: Array<String>,
        exit: String?,
        alertDo: OnAlertSelectId,
        cancelListener: DialogInterface.OnCancelListener?
    ): Dialog ?{
        val cancel = context.getString(R.string.app_cancel)
        val dlg = Dialog(context, R.style.MMTheme_DataSheet)
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout =
            inflater.inflate(R.layout.alert_dialog_menu_layout, null) as LinearLayout
        val cFullFillWidth = 10000
        layout.minimumWidth = cFullFillWidth
        val list =
            layout.findViewById<View>(R.id.content_list) as ListView
        val adapter =
            AlertAdapter(context, title, items, exit, cancel)
        list.adapter = adapter
        list.dividerHeight = 0
        list.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                if (!(title == null || title == "") && position - 1 >= 0) {
                    alertDo.onClick(position - 1)
                    dlg.dismiss()
                    list.requestFocus()
                } else {
                    alertDo.onClick(position)
                    dlg.dismiss()
                    list.requestFocus()
                }
            }
        // set a large value put it in bottom
        val w = dlg.window
        val lp = w!!.attributes
        lp.x = 0
        val cMakeBottom = -1000
        lp.y = cMakeBottom
        lp.gravity = Gravity.BOTTOM
        dlg.onWindowAttributesChanged(lp)
        dlg.setCanceledOnTouchOutside(true)
        if (cancelListener != null) {
            dlg.setOnCancelListener(cancelListener)
        }
        dlg.setContentView(layout)
        dlg.show()
        return dlg
    }
}


internal class AlertAdapter(
    context: Context,
    title: String?,
    items: Array<String>?,
    exit: String?,
    cancel: String?
) :
    BaseAdapter() {
    private var items: MutableList<String>? = null
    private val types: IntArray

    // private boolean isSpecial = false;
    private var isTitle = false

    // private boolean isExit = false;
    private val context: Context
    override fun getCount(): Int {
        return items!!.size
    }

    override fun getItem(position: Int): Any {
        return items!![position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun isEnabled(position: Int): Boolean {
        return if (position == 0 && isTitle) {
            false
        } else {
            super.isEnabled(position)
        }
    }

    override fun getView(
        position: Int,
        convertView: View,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        val textString = getItem(position) as String
        val holder: ViewHolder
        val type = types[position]
        if ((convertView.tag as ViewHolder).type != type) {
            holder = ViewHolder()
            when (type) {
                TYPE_CANCEL -> {
                    convertView = View.inflate(
                        context,
                        R.layout.alert_dialog_menu_list_layout_cancel,
                        null
                    )
                }
                TYPE_BUTTON -> {
                    convertView =
                        View.inflate(context, R.layout.alert_dialog_menu_list_layout, null)
                }
                TYPE_TITLE -> {
                    convertView = View.inflate(
                        context,
                        R.layout.alert_dialog_menu_list_layout_title,
                        null
                    )
                }
                TYPE_EXIT -> {
                    convertView = View.inflate(
                        context,
                        R.layout.alert_dialog_menu_list_layout_special,
                        null
                    )
                }

                // holder.view = (LinearLayout) convertView.findViewById(R.id.popup_layout);
            }

            // holder.view = (LinearLayout) convertView.findViewById(R.id.popup_layout);
            holder.text = convertView.findViewById<View>(R.id.popup_text) as TextView
            holder.type = type
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        holder.text!!.text = textString
        return convertView
    }

    internal class ViewHolder {
        // LinearLayout view;
        var text: TextView? = null
        var type = 0
    }

    companion object {
        // private static final String TAG = "AlertAdapter";
        const val TYPE_BUTTON = 0
        const val TYPE_TITLE = 1
        const val TYPE_EXIT = 2
        const val TYPE_CANCEL = 3
        fun stringsToList(src: Array<String>?): MutableList<String>? {
            if (src == null || src.isEmpty()) {
                return null
            }
            val result: MutableList<String> =
                ArrayList()
            for (i in src.indices) {
                result.add(src[i])
            }
            return result
        }
    }

    init {
        if (items == null || items.isEmpty()) {
            this.items = ArrayList()
        } else {
            this.items = stringsToList(items)
        }
        types = IntArray(this.items!!.size + 3)
        this.context = context
        if (title != null && title != "") {
            types[0] = TYPE_TITLE
            isTitle = true
            this.items!!.add(0, title)
        }
        if (exit != null && exit != "") {
            // this.isExit = true;
            types[this.items!!.size] = TYPE_EXIT
            this.items!!.add(exit)
        }
        if (cancel != null && cancel != "") {
            // this.isSpecial = true;
            types[this.items!!.size] = TYPE_CANCEL
            this.items!!.add(cancel)
        }
    }
}