package tdc.edu.ShoppingSearch;

import tdc.edu.danhsachsp.HangHoa;

public interface OnDeleteFromCartClickListener {

    void onDeleteCartItemClicked(HangHoa product);

    void onIncreaseCartItemClicked(HangHoa product);

    void onDecreaseCartItemClicked(HangHoa product);
}
