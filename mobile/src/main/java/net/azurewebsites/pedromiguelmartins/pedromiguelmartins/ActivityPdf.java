package net.azurewebsites.pedromiguelmartins.pedromiguelmartins;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

public class ActivityPdf extends net.sf.andpdf.pdfviewer.PdfViewerActivity {
    @Override
    public int getPreviousPageImageResource() { return R.drawable.left_arrow; }
    @Override
    public int getNextPageImageResource() { return R.drawable.right_arrow; }
    @Override
    public int getZoomInImageResource() { return R.drawable.zoom_in; }
    @Override
    public int getZoomOutImageResource() { return R.drawable.zoom_out; }
    @Override
    public int getPdfPasswordLayoutResource() { return R.layout.pdf_file_password; }
    @Override
    public int getPdfPageNumberResource() { return R.layout.dialog_pagenumber; }
    @Override
    public int getPdfPasswordEditField() { return R.id.etPassword; }
    @Override
    public int getPdfPasswordOkButton() { return R.id.btOK; }
    @Override
    public int getPdfPasswordExitButton() { return R.id.btExit; }
    @Override
    public int getPdfPageNumberEditField() { return R.id.pagenum_edit; }



}
