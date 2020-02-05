/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rle;

/**
 *
 * @author saad
 */
class pair<T, C>
{
    T first;
    C second;
    pair(){this.first = null; this.second = null;}

    pair(T first, C second)
    {
        this.first = first;
        this.second = second;
    }
}
