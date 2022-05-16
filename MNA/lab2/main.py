import math


def myfun(x):
    return (x) + 3*math.cos(x) - 1


def fun_der1(x):
    return 1 - 3 * math.sin(x)


def fun_der2(x):
    return -(3*math.cos(x))

def Lagrange_ravn_coef(fun, a, b, n):
    coefs = []
    for i in range(0,n + 1):
        xi = a + (b - a) * i / n
        c = fun(xi)
        for j in range(0,n + 1):
            if i != j:
                xj = a + (b - a) * j / n
                c *= 1 / (xi - xj)
        coefs.append(c)
    return coefs


def Lagrange_ravn(a, b, n, coefs, x):
    res = 0
    for i in range(0, n + 1):
        c = 1
        for j in range(0, n + 1):
            if i != j:
                xj = a + (b - a) * j / n
                c *= x - xj
        res += c * coefs[i]
    return res


def Lagrange_optimal_coef(fun, a, b, n):
    coefs  = []
    for i in range(0,n + 1):
        xi = ((a + b) / 2 + (b - a) * math.cos(math.pi * (2 * i + 1) /
              (2 * (n + 1))) / 2)
        c = fun(xi)
        for j in range(0,n + 1):
            if i != j:
                xj = ((a + b) / 2 + (b - a) * math.cos(math.pi * (2 * j + 1) /
              (2 * (n + 1))) / 2)
                c *= 1 / (xi - xj)
        coefs.append(c)
    return coefs


def Lagrange_optimal(a, b, n, coefs, x):
    res = 0
    for i in range(0, n + 1):
        c = 1
        for j in range(0, n + 1):
            if i != j:
                xj = ((a + b) / 2 + (b - a) * math.cos(math.pi * (2 * j + 1) /
              (2 * (n + 1))) / 2)
                c *= x - xj
        res += c * coefs[i]
    return res


def polinom_Lagrange(x, coef, n):
    """Make Lagrange's Polinom."""
    lagrcf = []
    for i in range(0, n + 1):
        lagrcf.append(0)
    for i in range(0, n + 1):
        O = []
        for j in range(0, n + 1):
            O.append(0)
        polinom(x, O, i)
        for j in range(0, n + 1):
            lagrcf[j] += coef[i] * O[j]
        print(str(x[i]) + "  " + str(coef[i]))
    print("Многочлен Лагранжа: ")
    for i in range(0, n + 1):
        print(str(lagrcf[i]) + ' * x^' + str(n - i)),
    print()
    return lagrcf


def polinom(x, L, i):
    """Make polinom of composition (x-i) elements."""
    ad_coef = []
    for j in range(0, 11):
        ad_coef.append(0)
    k = 0
    for j in range(0, 12):
        if i != j:
            ad_coef[k] = (-1) * x[j]
            k += 1
    L[0] = 1
    for j in range(2, 11):
        L[j] = 0
    comp = 1
    for j in range(0, 11):
        L[1] += ad_coef[j]
        comp *= ad_coef[j]
    L[11] += comp
    for j1 in range(0, 11):
        for j2 in range(j1 + 1, 11):
            L[2] += ad_coef[j1] * ad_coef[j2]
            for j3 in range(j2 + 1, 11):
                L[3] += ad_coef[j1] * ad_coef[j2] * ad_coef[j3]
                for j4 in range(j3 + 1, 11):
                    L[4] += ad_coef[j1] * ad_coef[j2] * ad_coef[j3] * ad_coef[j4]
                    for j5 in range(j4 + 1, 11):
                        L[5] += (ad_coef[j1] * ad_coef[j2] * ad_coef[j3]
                                 * ad_coef[j4] * ad_coef[j5])
                        for j6 in range(j5 + 1, 11):
                            L[6] += (ad_coef[j1] * ad_coef[j2] * ad_coef[j3]
                                 * ad_coef[j4] * ad_coef[j5] * ad_coef[j6])
                            for j7 in range(j6 + 1, 11):
                                L[7] += (ad_coef[j1] * ad_coef[j2] * ad_coef[j3]
                                 * ad_coef[j4] * ad_coef[j5] * ad_coef[j6]
                                 * ad_coef[j7])
                                for j8 in range(j7 + 1, 11):
                                    L[8] += (ad_coef[j1] * ad_coef[j2] * ad_coef[j3]
                                 * ad_coef[j4] * ad_coef[j5] * ad_coef[j6]
                                 * ad_coef[j7] * ad_coef[j8])
                                    for j9 in range(j8 + 1, 11):
                                        L[9] += (ad_coef[j1] * ad_coef[j2] * ad_coef[j3]
                                 * ad_coef[j4] * ad_coef[j5] * ad_coef[j6]
                                 * ad_coef[j7] * ad_coef[j8] * ad_coef[j9])
                                        for j10 in range(j9 +1, 11):
                                            L[10] += (ad_coef[j1] * ad_coef[j2] * ad_coef[j3]
                                 * ad_coef[j4] * ad_coef[j5] * ad_coef[j6]
                                 * ad_coef[j7] * ad_coef[j8] * ad_coef[j9] * ad_coef[j10])


def Thomas(A, f, x):
    """Thomas method solving systems of linear equation."""
    a = []
    b = []
    a.append((-1) * A[0][1] / A[0][0])
    b.append(f[0] / A[0][0])
    for i in range(1, 11):
        a.append((-1) * A[i][i + 1] / A[i][i] - a[i - 1] * (-1) * A[i][i - 1])
    for i in range(1, 12):
        t = f[i] - A[i][i - 1] * b[i - 1]
        w = A[i][i] + a[i - 1] * A[i][i - 1]
        b.append(t / w)
    x[11] = b[11]
    for i in range(10, -1, -1):
        x[i] = a[i] * x[i + 1] + b[i]


def Splain(x, M, i, X, h):
    """Splain."""
    r = 0
    r += M[i - 1] * (x[i] - X) ** 3 / (6 * h)
    r += M[i] * (X - x[i - 1]) ** 3 / (6 * h)
    r += (myfun(x[i]) - M[i] * h * h / 6) * (X - x[i - 1]) / h
    r += (myfun(x[i - 1]) - M[i - 1] * h * h / 6) * (x[i] - X) / h
    print(str(M[i - 1] / (6 * h)))
    print(str(x[i]))
    print(str(M[i] / (6 * h)))
    print(str(x[i - 1]))
    print(str((myfun(x[i]) - (M[i] * h * h) / 6) / h))
    print(str(x[i - 1]))
    print(str((myfun(x[i - 1]) - (M[i - 1] * h * h) / 6) / h))
    print(str(x[i]))
    print()
    return r


a = 0
b = 1
n = 11
z = a + (b - a) / 22
coefs = Lagrange_ravn_coef(myfun, a, b, n)
print(coefs)
coefs1 = Lagrange_optimal_coef(myfun, a, b, n)
print(coefs1)
x = []
for i in range(0, n + 1):
    x.append(a + ((b - a) / 11) * i)
lagrcf = polinom_Lagrange(x, coefs, n)
for i in x:
    val = 0
    for j in range(0, n + 1):
        val += (i ** (n - j)) * lagrcf[j]
    print(str(i) + ', Lagrange: ' + str(val) + ', function: ' + str(myfun(i))
          + ', difference: ' + str(abs(myfun(i) - val)))
x1 = []
for i in range(0, n + 1):
    x1.append((a + b) / 2 + (b - a) * math.cos(math.pi * (2 * i + 1) /
              (2 * (n + 1))) / 2)
lagrcf1 = polinom_Lagrange(x1, coefs1, n)
for i in x1:
    val = 0
    for j in range(0, n + 1):
        val += (i ** (n - j)) * lagrcf1[j]
    print(str(i) + ', Lagrange optimal: ' + str(val) + ', function: ' + str(myfun(i))
          + ', difference: ' + str(abs(myfun(i) - val)))
h = (b - a) / n
A = [[0 for i in range(n + 1)] for j in range(n + 1)]
f = [0 for i in range(n + 1)]
O = [0 for i in range(n + 1)]
A[1][0] = h / 6
A[n - 1][n] = h / 6
for i in range(1, n):
    for j in range(1, n):
        A[i][j] = 0
        if i == j + 1 or i - 1 == j:
            A[i][j] = h / 6
        if i == j:
            A[i][j] = 2 * h / 3
        if i == j - 1 or i + 1 == j:
            A[i][j] = h / 6
    f[i] = (myfun(x[i + 1]) - myfun(x[i])) / h - (myfun(x[i]) - myfun(x[i - 1])) / h

A[0][0] = 1
A[0][1] = 0
A[n][n] = 1
A[n][n - 1] = 0
f[0] = 0
f[n] = 0
Thomas(A, f, O)
S1 = Splain(x, O, 1, z, h)
print()

A[0][0] = h / 3
A[0][1] = h / 6
A[n][n] = h / 3
A[n][n - 1] = h / 6
f[0] = (myfun(x[1]) - myfun(x[0])) / h - fun_der1(x[0])
f[n] = fun_der1(x[n]) - (myfun(x[n]) - myfun(x[n - 1])) / h
Thomas(A, f, O)
S2 = Splain(x, O, 1, z, h)

A[0][0] = 1
A[0][1] = 0
A[n][n] = 1
A[n][n - 1] = 0
f[0] = fun_der2(x[0])
f[n] = fun_der2(x[n])
Thomas(A, f, O)
S3 = Splain(x, O, 1, z, h)
t = 0
for i in range(n + 1):
    deg = z ** (n - i)
    t += deg * lagrcf[i]
t1 = 0
for i in range(n + 1):
    deg = z ** (n - i)
    t1 += deg * lagrcf1[i]
print(str(z) + ' ' + str(myfun(z)))
print('Lagrang ravn: ' + str(t) + ' ' + str(abs(t - myfun(z))))
print('Lagrang optimal: ' + str(t1) + ' ' + str(abs(t1 - myfun(z))))
print('Splain1: ' + str(S1) + ' ' + str(abs(S1 - myfun(z))))
print('Splain2: ' + str(S2) + ' ' + str(abs(S2 - myfun(z))))
print('Splain3: ' + str(S3) + ' ' + str(abs(S3 - myfun(z))))

