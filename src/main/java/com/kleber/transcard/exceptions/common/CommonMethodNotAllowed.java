package com.kleber.transcard.exceptions.common;

import com.kleber.transcard.exceptions.BaseException;

public class CommonMethodNotAllowed extends BaseException {
    public CommonMethodNotAllowed(){ super("Método não permitido para este endpoint"); }
}
