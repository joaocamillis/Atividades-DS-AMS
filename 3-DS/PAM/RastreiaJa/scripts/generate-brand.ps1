Add-Type -AssemblyName System.Drawing
$rastreiaRoot = Split-Path -Parent $PSScriptRoot
$rastreiaAssetPath = Join-Path $rastreiaRoot 'assets/brand'
[IO.Directory]::CreateDirectory($rastreiaAssetPath) | Out-Null

function New-RastreiaIcon([string]$Name, [int]$Size, [bool]$Background, [bool]$Monochrome) {
  $bitmap = [Drawing.Bitmap]::new($Size, $Size)
  $graphics = [Drawing.Graphics]::FromImage($bitmap)
  $graphics.SmoothingMode = [Drawing.Drawing2D.SmoothingMode]::AntiAlias
  $graphics.Clear([Drawing.Color]::Transparent)
  if ($Background) { $graphics.Clear([Drawing.ColorTranslator]::FromHtml('#14283F')) }
  $graphics.TranslateTransform($Size * 0.2, $Size * 0.2)
  $graphics.ScaleTransform($Size * 0.6 / 32, $Size * 0.6 / 32)
  $pen = [Drawing.Pen]::new([Drawing.Color]::White, 3)
  $pen.StartCap = [Drawing.Drawing2D.LineCap]::Round
  $pen.EndCap = [Drawing.Drawing2D.LineCap]::Round
  $pen.LineJoin = [Drawing.Drawing2D.LineJoin]::Round
  $path = [Drawing.Drawing2D.GraphicsPath]::new()
  $path.AddLine(10, 24, 10, 11)
  $path.AddBezier(10, 11, 10, 8, 11, 7, 14, 7)
  $path.AddLine(14, 7, 18, 7)
  $path.AddBezier(18, 7, 25, 7, 25, 17, 18, 17)
  $path.AddLine(18, 17, 14, 17)
  $path.AddLine(14, 17, 22, 24)
  $graphics.DrawPath($pen, $path)
  $dotColor = if ($Monochrome) { [Drawing.Color]::White } else { [Drawing.ColorTranslator]::FromHtml('#21967A') }
  $brush = [Drawing.SolidBrush]::new($dotColor)
  $graphics.FillEllipse($brush, 7, 21, 6, 6)
  $bitmap.Save((Join-Path $rastreiaAssetPath $Name), [Drawing.Imaging.ImageFormat]::Png)
  $brush.Dispose()
  $path.Dispose()
  $pen.Dispose()
  $graphics.Dispose()
  $bitmap.Dispose()
}

New-RastreiaIcon 'icon.png' 1024 $true $false
New-RastreiaIcon 'adaptive-icon.png' 1024 $false $false
New-RastreiaIcon 'monochrome-icon.png' 1024 $false $true
New-RastreiaIcon 'notification-icon.png' 96 $false $true
