// !WITH_NEW_INFERENCE
import java.util.ArrayList

@ArrayList<Int>(1, 1) fun b() {}
@<!OTHER_ERROR!>Xoo<!>(<!UNRESOLVED_REFERENCE!>x<!>) fun c() {}
@java.lang.Deprecated(<!UNRESOLVED_REFERENCE!>x<!>) fun a() {}